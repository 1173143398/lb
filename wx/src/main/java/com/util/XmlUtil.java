package com.util;

import java.io.Writer;

import com.message.IMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import com.xml.XmlUtf8XStream;

public class XmlUtil {

	//XStream方式转Bean
	public static <T> T xmlToBean(String xml,Class<T> requiredType) {
		XStream xstream=new XStream(){
		    @Override
		    protected MapperWrapper wrapMapper(MapperWrapper next) {
		    	//过滤多余XML字段
		        return new MapperWrapper(next) {
		            @Override
		            public boolean shouldSerializeMember(Class definedIn, String fieldName) {
		                if (definedIn == Object.class) {
		                    return false;
		                }
		                return super.shouldSerializeMember(definedIn, fieldName);
		            }
		        };
		    }
		};
		xstream.processAnnotations(requiredType);
		T obj=(T)xstream.fromXML(xml);
		return obj;            
	}
	//JAXBContext方式转Bean
//	public static <T> T xmlToBean(String xml,Class<T> requiredType) {
//		try {
//			JAXBContext context = JAXBContext.newInstance(requiredType);
//			Unmarshaller createUnmarshaller = context.createUnmarshaller();
//			T t = (T)createUnmarshaller.unmarshal(new StringReader(xml));
//			return t;
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	//XStream方式转XML
	public static String beantoXml(IMessage message,Class<? extends IMessage> requiredType) {
		XStream xstream=new XmlUtf8XStream(new XppDriver(){
			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out) {  
                    protected void writeText(QuickWriter writer, String text) {  
                        writer.write("<![CDATA[" + text + "]]>");  
                    }  
                };
			}
		});
		xstream.processAnnotations(requiredType); 
		return xstream.toXML(message);
	}
	
	//JAXBContext方式转XML
//	public static String beantoXml(IMessage message,Class<? extends IMessage> requiredType) {
//		try {
//			JAXBContext context = JAXBContext.newInstance(requiredType);
//			Marshaller marshaller = context.createMarshaller();  
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
//			//为了<![CDATA[fromUser]]>不转成&lt;![fromUser]]&gt;
//			marshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler", new com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler() {
//
//				@Override
//				public void escape(char[] ch, int start, int length, boolean isAttVal, Writer writer) throws IOException {
//					writer.write(ch, start, length);
//				}});
//			StringWriter writer = new StringWriter();  
//			marshaller.marshal(message, writer);  
//			return writer.toString(); 
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}  
//		return null;
//	}
}
