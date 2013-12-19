/**
 * FeedMappingServiceInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Mar 02, 2009 (07:08:06 PST) WSDL2Java emitter.
 */

package com.google.api.ads.adwords.axis.v201306.cm;

public interface FeedMappingServiceInterface extends java.rmi.Remote {

    /**
     * Returns a list of FeedMappings that meet the selector criteria.
     * 
     *         
     * @param selector Determines which FeedMappings to return. If empty
     * all
     *         FeedMappings are returned.
     *         
     * @return The list of FeedMappings.
     *         
     * @throws ApiException indicates a problem with the request.
     */
    public com.google.api.ads.adwords.axis.v201306.cm.FeedMappingPage get(com.google.api.ads.adwords.axis.v201306.cm.Selector selector) throws java.rmi.RemoteException, com.google.api.ads.adwords.axis.v201306.cm.ApiException;

    /**
     * Add and remove FeedMappings.
     *         
     *         
     * @param operations The operations to apply.
     *         
     * @return The resulting FeedMappings.
     *         
     * @throws ApiException indicates a problem with the request.
     */
    public com.google.api.ads.adwords.axis.v201306.cm.FeedMappingReturnValue mutate(com.google.api.ads.adwords.axis.v201306.cm.FeedMappingOperation[] operations) throws java.rmi.RemoteException, com.google.api.ads.adwords.axis.v201306.cm.ApiException;
}
