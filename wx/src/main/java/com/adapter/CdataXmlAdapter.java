package com.adapter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CdataXmlAdapter implements Converter {

	/** 
     * java对象转换为xml 
     */  
    public void marshal(Object object, HierarchicalStreamWriter writer,  
            MarshallingContext context) {  
          
        String prefix = "<![CDATA[";  
        String suffix = "]]>";  
        String trans = prefix + object + suffix;  
        writer.setValue(trans);  
    }  
  
    /** 
     * xml转换成JAVA对象 
     */  
    public Object unmarshal(HierarchicalStreamReader reader,  
            UnmarshallingContext context) {  
  
        return reader.getValue();  
    }  
  
    /** 
     * 判断字段是否是需要转换的类型 
     */  
    public boolean canConvert(Class paramClass) {  
        return String.class.isAssignableFrom(paramClass);  
    }  

}
