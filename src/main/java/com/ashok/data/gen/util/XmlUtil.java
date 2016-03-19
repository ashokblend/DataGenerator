package com.ashok.data.gen.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ashok.data.gen.constant.DataGenerationConstant;
import com.ashok.data.gen.exception.GenerationException;
import com.ashok.data.gen.model.Column;
import com.ashok.data.gen.model.DataType;

public class XmlUtil
{

	public static Document getXmlDocument(String configXmlPath)
			throws GenerationException
	{
		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try
		{
			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			return db.parse(configXmlPath);

		}
		catch (ParserConfigurationException pce)
		{
			throw new GenerationException("Failed to parse config xml:"
					+ pce.getMessage());
		}
		catch (SAXException se)
		{
			throw new GenerationException("Failed to parse config xml:"
					+ se.getMessage());
		}
		catch (IOException ioe)
		{
			throw new GenerationException("Failed to read config xml:"
					+ ioe.getMessage());
		}

	}

	/**
	 * I take a xml element and the tag name, look for the tag and get the text
	 * content i.e for <Column><name>name</name></Column> xml snippet if the
	 * Element points to employee node and tagName is 'name' I will return name
	 */
	public static String getTextValue(Element ele, String tagName)
	{
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0)
		{
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
	
	public static int getIntValue(Element ele,String tagName)
	{
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0)
		{
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return Integer.parseInt(textVal);
		
	}

	public static Column getColumn(Element columnNode)
	{
		//for each <column> element get text or int values of
		//name ,cardinality, template and datatype
		String name = getTextValue(columnNode,DataGenerationConstant.COLUMN_NAME);
		int cardinality = getIntValue(columnNode,DataGenerationConstant.COLUMN_CARDINALITY);
		String template = getTextValue(columnNode, DataGenerationConstant.COLUMN_TEMPLATE);
		String dataType = getTextValue(columnNode,DataGenerationConstant.COLUMN_DATA_TYPE);

		Column column = new Column(name,cardinality,DataType.getDataType(dataType),template);

		return column;
	}

}
