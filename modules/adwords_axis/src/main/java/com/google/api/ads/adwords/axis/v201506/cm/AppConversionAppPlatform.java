/**
 * AppConversionAppPlatform.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Mar 02, 2009 (07:08:06 PST) WSDL2Java emitter.
 */

package com.google.api.ads.adwords.axis.v201506.cm;

public class AppConversionAppPlatform implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected AppConversionAppPlatform(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _NONE = "NONE";
    public static final java.lang.String _ITUNES = "ITUNES";
    public static final java.lang.String _ANDROID_MARKET = "ANDROID_MARKET";
    public static final java.lang.String _MOBILE_APP_CHANNEL = "MOBILE_APP_CHANNEL";
    public static final AppConversionAppPlatform NONE = new AppConversionAppPlatform(_NONE);
    public static final AppConversionAppPlatform ITUNES = new AppConversionAppPlatform(_ITUNES);
    public static final AppConversionAppPlatform ANDROID_MARKET = new AppConversionAppPlatform(_ANDROID_MARKET);
    public static final AppConversionAppPlatform MOBILE_APP_CHANNEL = new AppConversionAppPlatform(_MOBILE_APP_CHANNEL);
    public java.lang.String getValue() { return _value_;}
    public static AppConversionAppPlatform fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        AppConversionAppPlatform enumeration = (AppConversionAppPlatform)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static AppConversionAppPlatform fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AppConversionAppPlatform.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://adwords.google.com/api/adwords/cm/v201506", "AppConversion.AppPlatform"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
