// Copyright 2011, Google Inc. All Rights Reserved.
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

package com.google.api.ads.common.lib.factory;

import com.google.api.ads.common.lib.client.AdsServiceClient;
import com.google.api.ads.common.lib.client.AdsServiceDescriptor;
import com.google.api.ads.common.lib.client.AdsSession;
import com.google.api.ads.common.lib.factory.helper.AdsServiceClientFactoryHelper;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.FactoryProvider;

/**
 * Module for factories.
 *
 * @param <C> the subclass of {@link AdsServiceClient}
 * @param <D> the subclass of {@link AdsServiceDescriptor}
 * @param <S> the subclass of {@link AdsSession}
 * @param <H> the subclass of {@link AdsServiceClientFactoryHelper}
 */
public class FactoryModule<C extends AdsServiceClient<S, D>,
                           D extends AdsServiceDescriptor,
                           S extends AdsSession,
                           H extends AdsServiceClientFactoryHelper<C, S, D>>
    extends AbstractModule {

  @SuppressWarnings("rawtypes") /* Guice lacks support for template type literals.*/
  private final TypeLiteral adsServiceClientFactoryTypeLiteral;
  @SuppressWarnings("rawtypes") /* Guice lacks support for template type literals.*/
  private final TypeLiteral adsServiceDescriptorFactoryTypeLiteral;
  @SuppressWarnings("rawtypes") /* Guice lacks support for template type literals.*/
  private final TypeLiteral adsServiceClientTypeLiteral;
  @SuppressWarnings("rawtypes") /* Guice lacks support for template type literals.*/
  private final TypeLiteral adsServiceDescriptorTypeLiteral;
  private final Class<H> factoryHelperClass;
  private final Class<?> factoryClass;

  /**
   * Constructor.
   *
   * @param adsServiceClientFactoryTypeLiteral the factory type literal which
   *     contains a {@link AdsServiceClientFactoryInterface}
   * @param adsServiceDescriptorFactoryTypeLiteral the factory type literal
   *     which contains a {@link AdsServiceDescriptorFactoryInterface}
   * @param adsServiceClientTypeLiteral the ads service client literal which
   *     contains a {@link AdsServiceClient}
   * @param adsServiceDescriptorTypeLiteral the ads service descriptor literal
   *     which contains a {@link AdsServiceDescriptor}
   * @param factoryHelperClass the factory helper class
   * @param factoryClass the factory class
   */
  public FactoryModule(
      @SuppressWarnings("rawtypes") /* Guice lacks support for template type literals.*/
      TypeLiteral adsServiceClientFactoryTypeLiteral,
      @SuppressWarnings("rawtypes") /* Guice lacks support for template type literals.*/
      TypeLiteral adsServiceDescriptorFactoryTypeLiteral,
      @SuppressWarnings("rawtypes") /* Guice lacks support for template type literals.*/
      TypeLiteral adsServiceClientTypeLiteral,
      @SuppressWarnings("rawtypes") /* Guice lacks support for template type literals.*/
      TypeLiteral adsServiceDescriptorTypeLiteral,
      Class<H> factoryHelperClass,
      Class<?> factoryClass) {
    this.adsServiceClientFactoryTypeLiteral = adsServiceClientFactoryTypeLiteral;
    this.adsServiceDescriptorFactoryTypeLiteral = adsServiceDescriptorFactoryTypeLiteral;
    this.adsServiceClientTypeLiteral = adsServiceClientTypeLiteral;
    this.adsServiceDescriptorTypeLiteral = adsServiceDescriptorTypeLiteral;
    this.factoryHelperClass = factoryHelperClass;
    this.factoryClass = factoryClass;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void configure() {
    // Bind the base Guice factory interface to its typed one.
    bind(adsServiceClientFactoryTypeLiteral).toProvider(
      FactoryProvider.newFactory(adsServiceClientFactoryTypeLiteral,
            adsServiceClientTypeLiteral));
    bind(adsServiceDescriptorFactoryTypeLiteral).toProvider(
        FactoryProvider.newFactory(adsServiceDescriptorFactoryTypeLiteral,
            adsServiceDescriptorTypeLiteral));
    bind(AdsServiceClientFactoryHelper.class).to(factoryHelperClass);
    bind(factoryClass);
  }

  /**
   * Factory interface class to create {@link AdsServiceClient}.
   *
   * @param <C> the subclass of {@link AdsServiceClient}
   * @param <S> the subclass of {@link AdsSession}
   * @param <D> the subclass of {@link AdsServiceDescriptor}
   */
  public static interface AdsServiceClientFactoryInterface<C extends AdsServiceClient<S, D>,
                                                           S extends AdsSession,
                                                           D extends AdsServiceDescriptor> {

    /**
     * Creates an {@link AdsServiceClient} given a SOAP client and an
     * {@link AdsServiceDescriptor} descriptor.
     *
     * @param soapClient the SOAP client underlying this service client
     * @param adsServiceDescriptor the service to get the client for
     * @param adsSession the ads session
     * @return a client for the specified ads service
     */
    C create(@Assisted("soapClient") Object soapClient,
        @Assisted("adsServiceDescriptor") D adsServiceDescriptor,
        @Assisted("adsSession") S adsSession);
  }

  /**
   * Factory interface class to create {@link AdsServiceDescriptor}.
   *
   * @param <D> the subclass of {@link AdsServiceDescriptor}
   */
  public static interface AdsServiceDescriptorFactoryInterface<D extends AdsServiceDescriptor>{

    /**
     * Creates an ads service descriptor given an interface class.
     *
     * @param interfaceClass the interface class for the descriptor.
     * @param version the version of the service
     * @return the ads service descriptor
     */
    D create(@Assisted("interfaceClass") Class<?> interfaceClass,
        @Assisted("version") String version);
  }
}
