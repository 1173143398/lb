package com.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.execpt.WxException;
import com.message.IMessage;

public class XmlUtil {

	private static Log log = LogFactory.getLog(XmlUtil.class);
	//JAXBContext方式转Bean
	public static <T> T xmlToBean(String xml,Class<T> requiredType) {
		try {
			JAXBContext context = JAXBContext.newInstance(requiredType);
			Unmarshaller createUnmarshaller = context.createUnmarshaller();
			T t = (T)createUnmarshaller.unmarshal(new StringReader(xml));
			return t;
		} catch (JAXBException e) {
			log.error("xml转换bean异常",e);
			throw new WxException("xml转换bean异常");
		}
	}
	
	//JAXBContext方式转XML
	public static String beantoXml(IMessage message,Class<? extends IMessage> requiredType) {
		try {
			JAXBContext context = JAXBContext.newInstance(requiredType);
			Marshaller marshaller = context.createMarshaller();  
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
			//为了<![CDATA[fromUser]]>不转成&lt;![fromUser]]&gt;
			marshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler", new com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler() {

				@Override
				public void escape(char[] ch, int start, int length, boolean isAttVal, Writer writer) throws IOException {
					writer.write(ch, start, length);
				}});
			StringWriter writer = new StringWriter();  
			marshaller.marshal(message, writer);  
			return writer.toString(); 
		} catch (JAXBException e) {
			log.error("bean转换xml异常",e);
			throw new WxException("bean转换xml异常");
		}  
	}
}
