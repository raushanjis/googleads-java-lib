
package com.google.api.ads.dfp.jaxws.v201308;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "LineItemService", targetNamespace = "https://www.google.com/apis/ads/publisher/v201308", wsdlLocation = "https://www.google.com/apis/ads/publisher/v201308/LineItemService?wsdl")
public class LineItemService
    extends Service
{

    private final static URL LINEITEMSERVICE_WSDL_LOCATION;
    private final static WebServiceException LINEITEMSERVICE_EXCEPTION;
    private final static QName LINEITEMSERVICE_QNAME = new QName("https://www.google.com/apis/ads/publisher/v201308", "LineItemService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://www.google.com/apis/ads/publisher/v201308/LineItemService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LINEITEMSERVICE_WSDL_LOCATION = url;
        LINEITEMSERVICE_EXCEPTION = e;
    }

    public LineItemService() {
        super(__getWsdlLocation(), LINEITEMSERVICE_QNAME);
    }

    public LineItemService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns LineItemServiceInterface
     */
    @WebEndpoint(name = "LineItemServiceInterfacePort")
    public LineItemServiceInterface getLineItemServiceInterfacePort() {
        return super.getPort(new QName("https://www.google.com/apis/ads/publisher/v201308", "LineItemServiceInterfacePort"), LineItemServiceInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LineItemServiceInterface
     */
    @WebEndpoint(name = "LineItemServiceInterfacePort")
    public LineItemServiceInterface getLineItemServiceInterfacePort(WebServiceFeature... features) {
        return super.getPort(new QName("https://www.google.com/apis/ads/publisher/v201308", "LineItemServiceInterfacePort"), LineItemServiceInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LINEITEMSERVICE_EXCEPTION!= null) {
            throw LINEITEMSERVICE_EXCEPTION;
        }
        return LINEITEMSERVICE_WSDL_LOCATION;
    }

}
