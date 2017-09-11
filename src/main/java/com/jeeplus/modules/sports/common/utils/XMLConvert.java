/**
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved. 
 * This software is the confidential and proprietary information of Founder. 
 * You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 */
package com.jeeplus.modules.sports.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * XML杞崲
 * @author yin85@163.com
 * @date  2013-9-29 涓婂崍9:37:10
 */
public class XMLConvert {

	private static final Log _LOG = LogFactory.getLog(XMLConvert.class);
	
	/** 瀹炰緥鍖栧璞?*/
	public static XMLConvert xml = new XMLConvert();
	
	private static final Map<Class<?>, Marshaller> JAXB_MARSHALLER_MAP = new HashMap<Class<?>, Marshaller>();
	private static final Map<Class<?>, Unmarshaller> JAXB_UNMARSHALLER_MAP = new HashMap<Class<?>, Unmarshaller>();
	
	/**
	 * XML杞崲ObjClazz瀵硅薄
	 * @param xml XML鍐呭
	 * @param clazz 绫诲璞?
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <X> X xmlToObjClazz(String xml, Class<X> clazz) {
		try {
			Unmarshaller unmarshaller = JAXB_UNMARSHALLER_MAP.get(clazz);
			if (unmarshaller == null) {
				JAXBContext context = JAXBContext.newInstance(clazz);
				unmarshaller = context.createUnmarshaller();
				JAXB_UNMARSHALLER_MAP.put(clazz, unmarshaller);
			}
			StringReader reader = new StringReader(xml);
			return (X) unmarshaller.unmarshal(reader);
		}catch (Exception e) {
			_LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * ObjClazz杞崲涓篨ML
	 * @param objClazz
	 * @return
	 */
	public String objClazzToXml(Object objClazz) {
		try {
			Marshaller marshaller = JAXB_MARSHALLER_MAP.get(objClazz.getClass());
			if (marshaller == null) {
				JAXBContext context = JAXBContext.newInstance(objClazz.getClass());
				marshaller = context.createMarshaller();
				// 鏄惁鏍煎紡鍖栫敓鎴愮殑XML涓?
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				JAXB_MARSHALLER_MAP.put(objClazz.getClass(), marshaller);
			}
			StringWriter writer = new StringWriter();
			marshaller.marshal(objClazz, writer);
			String xml = writer.toString();
			return xml;
		}
		catch (Exception e) {
			_LOG.error(e.getMessage(), e);
		} 
		return null;
	}
	
}
