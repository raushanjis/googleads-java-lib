
package com.google.api.ads.dfp.jaxws.v201508;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * 
 *       Provides methods for estimating traffic (clicks/impressions) for line items.
 *       Forecasts can be provided for {@link LineItem} objects that exist in the
 *       system or which have not had an ID set yet.
 *       
 *       <h4>Test network behavior</h4>
 *       
 *       <p>Test networks are unable to provide forecasts that would be
 *       comparable to the production environment because forecasts require traffic
 *       history. For test networks, a consistent behavior can be expected
 *       for forecast requests, according to the following rules:
 *       
 *       <table>
 *       <tr>
 *       <th colspan="2">Inputs<br/>({@link LineItem} Fields)</th>
 *       <th colspan="4">Outputs<br/>({@link Forecast} Fields)</th>
 *       </tr>
 *       <tr>
 *       <th>{@link LineItem#lineItemType lineItemType}</th>
 *       <th>{@link LineItem#unitsBought unitsBought}</th>
 *       <th>{@link Forecast#availableUnits availableUnits}</th>
 *       <th>{@link Forecast#forecastUnits forecastUnits (matchedUnits)}</th>
 *       <th>{@link Forecast#deliveredUnits deliveredUnits}</th>
 *       <th>Exception</td>
 *       </tr>
 *       <tr>
 *       <td>Sponsorship</td>
 *       <td>13</td>
 *       <td>&ndash;&ndash;</td>
 *       <td>&ndash;&ndash;</td>
 *       <td>&ndash;&ndash;</td>
 *       <td>
 *       {@link ForecastError.Reason#NO_FORECAST_YET NO_FORECAST_YET}
 *       </td>
 *       </tr>
 *       <tr>
 *       <td>Sponsorship</td>
 *       <td>20</td>
 *       <td>&ndash;&ndash;</td>
 *       <td>&ndash;&ndash;</td>
 *       <td>&ndash;&ndash;</td>
 *       <td>
 *       {@link ForecastError.Reason#SERVER_NOT_AVAILABLE SERVER_NOT_AVAILABLE}
 *       </td>
 *       </tr>
 *       <tr>
 *       <td>Sponsorship</td>
 *       <td>50</td>
 *       <td>1,200,000</td>
 *       <td>6,000,000</td>
 *       <td>600,000<br/>For prospective: 0</td>
 *       <td>&ndash;&ndash;</td>
 *       </tr>
 *       <tr>
 *       <td>Sponsorship</td>
 *       <td>!= 20 and <br/> != 50</td>
 *       <td>1,200,000</td>
 *       <td>1,200,000</td>
 *       <td>600,000<br/>For prospective: 0</td>
 *       <td>&ndash;&ndash;</td>
 *       </tr>
 *       <tr>
 *       <td>Not Sponsorship</td>
 *       <td>&lt;= 500,000</td>
 *       <td>3 * unitsBought / 2</td>
 *       <td>unitsBought * 6</td>
 *       <td>600,000<br/>For prospective: 0</td>
 *       <td>&ndash;&ndash;</td>
 *       </tr>
 *       <tr>
 *       <td>Not Sponsorship</td>
 *       <td>&gt; 500,000 and &lt;= 1,000,000</td>
 *       <td>unitsBought / 2</td>
 *       <td>unitsBought * 6</td>
 *       <td>600,000<br/>For prospective: 0</td>
 *       <td>&ndash;&ndash;</td>
 *       </tr>
 *       <tr>
 *       <td>Not Sponsorship</td>
 *       <td>&gt; 1,000,000 and &lt;= 1,500,000</td>
 *       <td>unitsBought / 2</td>
 *       <td>3 * unitsBought / 2</td>
 *       <td>600,000<br/>For prospective: 0</td>
 *       <td>&ndash;&ndash;</td>
 *       </tr>
 *       <tr>
 *       <td>Not Sponsorship</td>
 *       <td>&gt; 1,500,000</td>
 *       <td>unitsBought / 4</td>
 *       <td>3 * unitsBought / 2</td>
 *       <td>600,000<br/>For prospective: 0</td>
 *       <td>&ndash;&ndash;</td>
 *       </tr>
 *       </table>
 *     
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ForecastServiceInterface", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ForecastServiceInterface {


    /**
     * 
     *         Gets the availability forecast for a {@link ProspectiveLineItem}. An availability forecast
     *         reports the maximum number of available units that the line item can book, and the total
     *         number of units matching the line item's targeting.
     *         
     *         <p>Note: Beginning in v201502, this replaces the previous getForecast method.
     *         
     *         @param lineItem the prospective line item (new or existing) to be forecasted for availability
     *         @param forecastOptions options controlling the forecast
     *       
     * 
     * @param lineItem
     * @param forecastOptions
     * @return
     *     returns com.google.api.ads.dfp.jaxws.v201508.AvailabilityForecast
     * @throws ApiException_Exception
     */
    @WebMethod
    @WebResult(name = "rval", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
    @RequestWrapper(localName = "getAvailabilityForecast", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508", className = "com.google.api.ads.dfp.jaxws.v201508.ForecastServiceInterfacegetAvailabilityForecast")
    @ResponseWrapper(localName = "getAvailabilityForecastResponse", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508", className = "com.google.api.ads.dfp.jaxws.v201508.ForecastServiceInterfacegetAvailabilityForecastResponse")
    public AvailabilityForecast getAvailabilityForecast(
        @WebParam(name = "lineItem", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
        ProspectiveLineItem lineItem,
        @WebParam(name = "forecastOptions", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
        AvailabilityForecastOptions forecastOptions)
        throws ApiException_Exception
    ;

    /**
     * 
     *         Gets an {@link AvailabilityForecast} for an existing {@link LineItem} object.
     *         An availability forecast reports the maximum number of available units that the line item can
     *         be booked with, and also the total number of units matching the line item's targeting.
     *         
     *         <p>Only line items having type {@link LineItemType#SPONSORSHIP} or
     *         {@link LineItemType#STANDARD} are valid. Other types will result in
     *         {@link ReservationDetailsError.Reason#LINE_ITEM_TYPE_NOT_ALLOWED}.
     *         
     *         <p>Note: Beginning in v201502, this replaces the previous getForecastById method.
     *         
     *         @param lineItemId the ID of a {@link LineItem} to run the forecast on.
     *         @param forecastOptions options controlling the forecast
     *       
     * 
     * @param forecastOptions
     * @param lineItemId
     * @return
     *     returns com.google.api.ads.dfp.jaxws.v201508.AvailabilityForecast
     * @throws ApiException_Exception
     */
    @WebMethod
    @WebResult(name = "rval", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
    @RequestWrapper(localName = "getAvailabilityForecastById", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508", className = "com.google.api.ads.dfp.jaxws.v201508.ForecastServiceInterfacegetAvailabilityForecastById")
    @ResponseWrapper(localName = "getAvailabilityForecastByIdResponse", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508", className = "com.google.api.ads.dfp.jaxws.v201508.ForecastServiceInterfacegetAvailabilityForecastByIdResponse")
    public AvailabilityForecast getAvailabilityForecastById(
        @WebParam(name = "lineItemId", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
        Long lineItemId,
        @WebParam(name = "forecastOptions", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
        AvailabilityForecastOptions forecastOptions)
        throws ApiException_Exception
    ;

    /**
     * 
     *         Gets the delivery forecast for a list of {@link ProspectiveLineItem} objects in a single
     *         delivery simulation with line items potentially contending with each other. A delivery
     *         forecast reports the number of units that will be delivered to each line item given the line
     *         item goals and contentions from other line items.
     *         
     *         @param lineItems line items to be forecasted for delivery
     *         @param forecastOptions options controlling the forecast
     *       
     * 
     * @param lineItems
     * @param forecastOptions
     * @return
     *     returns com.google.api.ads.dfp.jaxws.v201508.DeliveryForecast
     * @throws ApiException_Exception
     */
    @WebMethod
    @WebResult(name = "rval", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
    @RequestWrapper(localName = "getDeliveryForecast", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508", className = "com.google.api.ads.dfp.jaxws.v201508.ForecastServiceInterfacegetDeliveryForecast")
    @ResponseWrapper(localName = "getDeliveryForecastResponse", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508", className = "com.google.api.ads.dfp.jaxws.v201508.ForecastServiceInterfacegetDeliveryForecastResponse")
    public DeliveryForecast getDeliveryForecast(
        @WebParam(name = "lineItems", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
        List<ProspectiveLineItem> lineItems,
        @WebParam(name = "forecastOptions", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
        DeliveryForecastOptions forecastOptions)
        throws ApiException_Exception
    ;

    /**
     * 
     *         Gets the delivery forecast for a list of existing {@link LineItem} objects in a single
     *         delivery simulation with line items potentially contending with each other. A delivery
     *         forecast reports the number of units that will be delivered to each line item given the line
     *         item goals and contentions from other line items.
     *         
     *         @param lineItemIds the IDs of line items to be forecasted for delivery
     *         @param forecastOptions options controlling the forecast
     *       
     * 
     * @param forecastOptions
     * @param lineItemIds
     * @return
     *     returns com.google.api.ads.dfp.jaxws.v201508.DeliveryForecast
     * @throws ApiException_Exception
     */
    @WebMethod
    @WebResult(name = "rval", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
    @RequestWrapper(localName = "getDeliveryForecastByIds", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508", className = "com.google.api.ads.dfp.jaxws.v201508.ForecastServiceInterfacegetDeliveryForecastByIds")
    @ResponseWrapper(localName = "getDeliveryForecastByIdsResponse", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508", className = "com.google.api.ads.dfp.jaxws.v201508.ForecastServiceInterfacegetDeliveryForecastByIdsResponse")
    public DeliveryForecast getDeliveryForecastByIds(
        @WebParam(name = "lineItemIds", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
        List<Long> lineItemIds,
        @WebParam(name = "forecastOptions", targetNamespace = "https://www.google.com/apis/ads/publisher/v201508")
        DeliveryForecastOptions forecastOptions)
        throws ApiException_Exception
    ;

}
