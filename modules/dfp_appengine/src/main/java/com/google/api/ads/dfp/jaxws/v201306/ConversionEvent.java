
package com.google.api.ads.dfp.jaxws.v201306;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConversionEvent.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ConversionEvent">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CREATIVE_VIEW"/>
 *     &lt;enumeration value="START"/>
 *     &lt;enumeration value="SKIP_SHOWN"/>
 *     &lt;enumeration value="FIRST_QUARTILE"/>
 *     &lt;enumeration value="MIDPOINT"/>
 *     &lt;enumeration value="THIRD_QUARTILE"/>
 *     &lt;enumeration value="ENGAGED_VIEW"/>
 *     &lt;enumeration value="COMPLETE"/>
 *     &lt;enumeration value="MUTE"/>
 *     &lt;enumeration value="UNMUTE"/>
 *     &lt;enumeration value="PAUSE"/>
 *     &lt;enumeration value="REWIND"/>
 *     &lt;enumeration value="RESUME"/>
 *     &lt;enumeration value="SKIPPED"/>
 *     &lt;enumeration value="FULLSCREEN"/>
 *     &lt;enumeration value="EXPAND"/>
 *     &lt;enumeration value="COLLAPSE"/>
 *     &lt;enumeration value="ACCEPT_INVITATION"/>
 *     &lt;enumeration value="CLOSE"/>
 *     &lt;enumeration value="CLICK_TRACKING"/>
 *     &lt;enumeration value="SURVEY"/>
 *     &lt;enumeration value="CUSTOM_CLICK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ConversionEvent")
@XmlEnum
public enum ConversionEvent {


    /**
     * 
     *                 Corresponds to the {@code creativeView} tracking event.
     *               
     * 
     */
    CREATIVE_VIEW,

    /**
     * 
     *                 Corresponds to the {@code start} tracking event.
     *               
     * 
     */
    START,

    /**
     * 
     *                 An event that is fired when a video skip button is shown,
     *                 usually after 5 seconds of viewing the video.
     *                 This event does not correspond to any VAST element and is implemented using an extension.
     *               
     * 
     */
    SKIP_SHOWN,

    /**
     * 
     *                 Corresponds to the {@code firstQuartile} tracking event.
     *               
     * 
     */
    FIRST_QUARTILE,

    /**
     * 
     *                 Corresponds to the {@code midpoint} tracking event.
     *               
     * 
     */
    MIDPOINT,

    /**
     * 
     *                 Corresponds to the {@code thirdQuartile} tracking event.
     *               
     * 
     */
    THIRD_QUARTILE,

    /**
     * 
     *                 An event that is fired after 30 seconds of viewing the video
     *                 or when the video finished (if the video duration is less than 30 seconds).
     *                 This event does not correspond to any VAST element and is implemented using an extension.
     *               
     * 
     */
    ENGAGED_VIEW,

    /**
     * 
     *                 Corresponds to the {@code complete} tracking event.
     *               
     * 
     */
    COMPLETE,

    /**
     * 
     *                 Corresponds to the {@code mute} tracking event.
     *               
     * 
     */
    MUTE,

    /**
     * 
     *                 Corresponds to the {@code unmute} tracking event.
     *               
     * 
     */
    UNMUTE,

    /**
     * 
     *                 Corresponds to the {@code pause} tracking event.
     *               
     * 
     */
    PAUSE,

    /**
     * 
     *                 Corresponds to the {@code rewind} tracking event.
     *               
     * 
     */
    REWIND,

    /**
     * 
     *                 Corresponds to the {@code resume} tracking event.
     *               
     * 
     */
    RESUME,

    /**
     * 
     *                 An event that is fired when a video was skipped.
     *                 This event does not correspond to any VAST element and is implemented using an extension.
     *               
     * 
     */
    SKIPPED,

    /**
     * 
     *                 Corresponds to the {@code fullscreen} tracking event.
     *               
     * 
     */
    FULLSCREEN,

    /**
     * 
     *                 Corresponds to the {@code expand} tracking event.
     *               
     * 
     */
    EXPAND,

    /**
     * 
     *                 Corresponds to the {@code collapse} tracking event.
     *               
     * 
     */
    COLLAPSE,

    /**
     * 
     *                 Corresponds to the {@code acceptInvitation} tracking event.
     *               
     * 
     */
    ACCEPT_INVITATION,

    /**
     * 
     *                 Corresponds to the {@code close} tracking event.
     *               
     * 
     */
    CLOSE,

    /**
     * 
     *                 Corresponds to the {@code Linear.VideoClicks.ClickTracking} node.
     *               
     * 
     */
    CLICK_TRACKING,

    /**
     * 
     *                 Corresponds to the {@code InLine.Survey} node.
     *               
     * 
     */
    SURVEY,

    /**
     * 
     *                 Corresponds to the {@code Linear.VideoClicks.CustomClick} node.
     *               
     * 
     */
    CUSTOM_CLICK;

    public String value() {
        return name();
    }

    public static ConversionEvent fromValue(String v) {
        return valueOf(v);
    }

}
