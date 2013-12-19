// Copyright 2012 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.api.ads.common.lib.soap.jaxws;

import com.google.api.ads.common.lib.exception.ServiceException;
import com.google.api.ads.common.lib.soap.RequestInfo;
import com.google.api.ads.common.lib.soap.ResponseInfo;
import com.google.api.ads.common.lib.soap.SoapCall;
import com.google.api.ads.common.lib.soap.SoapCallReturn;
import com.google.api.ads.common.lib.soap.SoapClientHandler;
import com.google.api.ads.common.lib.soap.SoapClientHandlerInterface;
import com.google.api.ads.common.lib.soap.SoapServiceDescriptor;
import com.google.api.ads.common.lib.soap.compatability.JaxWsCompatible;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;

/**
 * SOAP Client Handler implementation for use with JAX-WS.
 *
 * @author Joseph DiLallo
 */
public class JaxWsHandler extends SoapClientHandler<BindingProvider> {

  /**
   * Default request timeout for AppEngine.
   */
  private static final int REQUEST_TIMEOUT = 10 * 60 * 1000;
  private static final String PRODUCTION_REQUEST_TIMEOUT_KEY = "com.sun.xml.ws.request.timeout";
  private static final String PRODUCTION_CONNECT_TIMEOUT_KEY = "com.sun.xml.ws.connect.timeout";
  private static final String DEVEL_REQUEST_TIMEOUT_KEY = "com.sun.xml.internal.ws.request.timeout";
  private static final String DEVEL_CONNECT_TIMEOUT_KEY = "com.sun.xml.internal.ws.connect.timeout";

  private JaxWsSoapContextHandlerFactory contextHandlerFactory;

  /**
   * Constructor.
   *
   * @param contextHandlerFactory a factory which produces context handlers
   */
  @Inject
  protected JaxWsHandler(JaxWsSoapContextHandlerFactory contextHandlerFactory) {
    super();
    this.contextHandlerFactory = contextHandlerFactory;
  }

  /**
   * Sets the endpoint address of the given SOAP client.
   *
   * @param soapClient the SOAP client to set the endpoint address for
   * @param endpointAddress the target endpoint address
   */
  public void setEndpointAddress(BindingProvider soapClient, String endpointAddress) {
    soapClient.getRequestContext().put(
        BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
  }

  /**
   * Returns a SOAP header from the given SOAP client, if it exists.
   *
   * @param soapClient the SOAP client to check for the given header
   * @param headerName the name of the header being looked for
   * @return the header element, if it exists
   */
  public Object getHeader(BindingProvider soapClient, String headerName) {
    for (SOAPElement addedHeader : getContextHandlerFromClient(soapClient).getAddedHeaders()) {
      if (addedHeader.getNodeName().equals(headerName)) {
        return addedHeader;
      }
    }
    return null;
  }

  /**
   * Clears all of the SOAP headers from the given SOAP client.
   *
   * @param soapClient the client to remove the headers from
   */
  public void clearHeaders(BindingProvider soapClient) {
    getContextHandlerFromClient(soapClient).clearHeaders();
    soapClient.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS,
        new HashMap<String, List<String>>());
  }

  /**
   * @see SoapClientHandler#setHeader(Object, String, String, Object)
   */
  public void setHeader(BindingProvider soapClient, String namespace, String headerName,
      Object headerValue) {
    if (headerValue instanceof SOAPElement) {
      getContextHandlerFromClient(soapClient).addHeader(namespace, headerName,
          (SOAPElement) headerValue);
    } else {
      throw new ServiceException("Unexpected SOAP header given for JAX-WS binding. Given "
          + "object of class \"" + headerValue.getClass().toString() + "\" but expecting "
          + "object of class \"" + SOAPElement.class + "\".", null);
    }
  }

  /**
   * @see SoapClientHandler#putAllHttpHeaders(Object, Map)
   */
  public void putAllHttpHeaders(BindingProvider soapClient, Map<String, String> headersMap) {
    @SuppressWarnings("unchecked") // HTTP Headers in JAXWS are always a map of
                                   // String to List of String.
    Map<String, List<String>> httpHeaders = (Map<String, List<String>>) soapClient
        .getRequestContext().get(MessageContext.HTTP_REQUEST_HEADERS);
    if (httpHeaders == null) {
      httpHeaders = Maps.newHashMap();
    }

    for (String key : headersMap.keySet()) {
      httpHeaders.put(key, Lists.newArrayList(headersMap.get(key)));
    }

    soapClient.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS,
        httpHeaders);
  }

  /**
   * Set whether SOAP requests should use compression.
   *
   * TODO(jdilallo): Add hook to this.
   *
   * @param soapClient the client to set compression settings for
   * @param compress whether or not to use compression
   */
  public void setCompression(BindingProvider soapClient, boolean compress) {
        Map<String, String> headersMap = Maps.newHashMap();
        if (compress) {
          headersMap.put("Accept-Encoding", "gzip");
          headersMap.put("Content-Encoding", "gzip");
          putAllHttpHeaders(soapClient, headersMap);
        } else {
          @SuppressWarnings("unchecked") // HTTP Headers in JAXWS are always a map of
                                         // String to List of String.
          Map<String, List<String>> httpHeaders =
              (Map<String, List<String>>) soapClient.getRequestContext().get(
                  MessageContext.HTTP_REQUEST_HEADERS);
          if (httpHeaders != null) {
            httpHeaders.remove("Accept-Encoding");
            httpHeaders.remove("Content-Encoding");
          }
        }

  }

  /**
   * Creates a SOAP client using a SOAP service descriptor.
   *
   * @param soapServiceDescriptor the descriptor to use for creating a client
   * @return the SOAP client for this descriptor
   * @throws ServiceException thrown if the SOAP client cannot be created
   */
  public BindingProvider createSoapClient(SoapServiceDescriptor soapServiceDescriptor)
      throws ServiceException {
    try {
      if (soapServiceDescriptor instanceof JaxWsCompatible) {
        JaxWsCompatible jaxWsCompatibleService = (JaxWsCompatible) soapServiceDescriptor;
        Object portLocator = jaxWsCompatibleService.getServiceClass()
            .getConstructor(new Class[0]).newInstance(new Object[0]);
        String interfaceClassName = soapServiceDescriptor.getInterfaceClass().getSimpleName();
        BindingProvider soapClient = (BindingProvider) portLocator.getClass()
            .getMethod("get" + interfaceClassName + "Port").invoke(portLocator);

        // Required for App Engine to avoid default 10s timeout for UrlFetch requests.
        setTimeOut(soapClient);

        @SuppressWarnings("rawtypes") // getHandlerChain returns a list of raw Handler.
        List<Handler> bindings = soapClient.getBinding().getHandlerChain();
        bindings.add(contextHandlerFactory.getJaxWsSoapContextHandler());
        soapClient.getBinding().setHandlerChain(bindings);
        return soapClient;
      }
      throw new ServiceException("Service [" + soapServiceDescriptor +
          "] is not compatible with JAX-WS", null);
    } catch (SecurityException e) {
      throw new ServiceException("Unexpected Exception.", e);
    } catch (NoSuchMethodException e) {
      throw new ServiceException("Unexpected Exception.", e);
    } catch (IllegalArgumentException e) {
      throw new ServiceException("Unexpected Exception.", e);
    } catch (IllegalAccessException e) {
      throw new ServiceException("Unexpected Exception.", e);
    } catch (InvocationTargetException e) {
      throw new ServiceException("Unexpected Exception.", e.getCause());
    } catch (ClassNotFoundException e) {
      throw new ServiceException("Unexpected Exception.", e);
    } catch (InstantiationException e) {
      throw new ServiceException("Unexpected Exception.", e);
    }
  }

  /**
   * Sets properties into the message context to alter the timeout on App Engine.
   */
  private void setTimeOut(BindingProvider bindingProvider) {
    // Production App Engine
    bindingProvider.getRequestContext().put(PRODUCTION_REQUEST_TIMEOUT_KEY, REQUEST_TIMEOUT);
    bindingProvider.getRequestContext().put(PRODUCTION_CONNECT_TIMEOUT_KEY, REQUEST_TIMEOUT);
    // Dev App Engine
    bindingProvider.getRequestContext().put(DEVEL_REQUEST_TIMEOUT_KEY, REQUEST_TIMEOUT);
    bindingProvider.getRequestContext().put(DEVEL_CONNECT_TIMEOUT_KEY, REQUEST_TIMEOUT);
  }

  /**
   * Invoke a SOAP call.
   *
   * @param soapCall the call to make to a SOAP web service
   * @return information about the SOAP response
   */
  public SoapCallReturn invokeSoapCall(SoapCall<BindingProvider> soapCall) {
    BindingProvider webService = soapCall.getSoapClient();
    SoapCallReturn.Builder builder = new SoapCallReturn.Builder();
    synchronized (webService) {
      Object result = null;
      try {
        result = invoke(soapCall);
      } catch (InvocationTargetException e) {
        builder.withException(e.getTargetException());
      } catch (Exception e) {
        builder.withException(e);
      } finally {
        JaxWsSoapContextHandler contextHandler = getContextHandlerFromClient(webService);
        builder.withRequestInfo(new RequestInfo.Builder()
            .withSoapRequestXml(contextHandler.getLastRequestXml())
            .withMethodName(contextHandler.getLastOperationCalled())
            .withServiceName(contextHandler.getLastServiceCalled())
            .withUrl((String) webService.getRequestContext().get(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY))
            .build());
        builder.withResponseInfo(new ResponseInfo.Builder().withSoapResponseXml(
            contextHandler.getLastResponseXml()).build());
      }
      return builder.withReturnValue(result).build();
    }
  }

  /**
   * @see SoapClientHandlerInterface#getEndpointAddress(Object)
   */
  public String getEndpointAddress(BindingProvider soapClient) {
    return (String) soapClient.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
  }

  /**
   * JAX-WS does not support use of this method.
   *
   * @see SoapClientHandlerInterface#createSoapHeaderElement(QName)
   */
  public SOAPHeaderElement createSoapHeaderElement(QName qName) {
    throw new UnsupportedOperationException();
  }

  /**
   * Extracts the {@link JaxWsSoapContextHandler} object from a SOAP client's
   * handler chain.
   *
   * In the event that no {@code JaxWsSoapContextHandler} object could be found,
   * this method throw an {@code IllegalStateException}.
   *
   * @param soapClient the JAX-WS soap client whose handler is needed
   * @return the {@code JaxWsSoapContextHandler} handler in the given client's
   *     handler chain
   */
  private JaxWsSoapContextHandler getContextHandlerFromClient(BindingProvider soapClient) {
    @SuppressWarnings("rawtypes") // getHandlerChain returns a list of raw Handler.
    List<Handler> handlers = soapClient.getBinding().getHandlerChain();
    for (Handler<?> handler : handlers) {
      if (handler instanceof JaxWsSoapContextHandler) {
        return (JaxWsSoapContextHandler) handler;
      }
    }
    throw new IllegalStateException("The SOAP client passed into the JaxWsHandler does not "
        + "have the necessary context handler on its binding chain.");
  }
}
