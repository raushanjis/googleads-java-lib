/**
 * PoddingErrorReason.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Mar 02, 2009 (07:08:06 PST) WSDL2Java emitter.
 */

package com.google.api.ads.dfp.axis.v201302;

public class PoddingErrorReason implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected PoddingErrorReason(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _INVALID_PODDING_TYPE_NONE = "INVALID_PODDING_TYPE_NONE";
    public static final java.lang.String _INVALID_PODDING_TYPE_STANDARD = "INVALID_PODDING_TYPE_STANDARD";
    public static final java.lang.String _INVALID_OPTIMIZED_POD_WITHOUT_DURATION = "INVALID_OPTIMIZED_POD_WITHOUT_DURATION";
    public static final java.lang.String _INVALID_OPTIMIZED_POD_WITHOUT_ADS = "INVALID_OPTIMIZED_POD_WITHOUT_ADS";
    public static final java.lang.String _INVALID_POD_DURATION_RANGE = "INVALID_POD_DURATION_RANGE";
    public static final PoddingErrorReason INVALID_PODDING_TYPE_NONE = new PoddingErrorReason(_INVALID_PODDING_TYPE_NONE);
    public static final PoddingErrorReason INVALID_PODDING_TYPE_STANDARD = new PoddingErrorReason(_INVALID_PODDING_TYPE_STANDARD);
    public static final PoddingErrorReason INVALID_OPTIMIZED_POD_WITHOUT_DURATION = new PoddingErrorReason(_INVALID_OPTIMIZED_POD_WITHOUT_DURATION);
    public static final PoddingErrorReason INVALID_OPTIMIZED_POD_WITHOUT_ADS = new PoddingErrorReason(_INVALID_OPTIMIZED_POD_WITHOUT_ADS);
    public static final PoddingErrorReason INVALID_POD_DURATION_RANGE = new PoddingErrorReason(_INVALID_POD_DURATION_RANGE);
    public java.lang.String getValue() { return _value_;}
    public static PoddingErrorReason fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        PoddingErrorReason enumeration = (PoddingErrorReason)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static PoddingErrorReason fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(PoddingErrorReason.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.google.com/apis/ads/publisher/v201302", "PoddingError.Reason"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
