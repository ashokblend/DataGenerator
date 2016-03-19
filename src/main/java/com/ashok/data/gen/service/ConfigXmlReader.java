package com.ashok.data.gen.service;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ashok.data.gen.constant.DataGenerationConstant;
import com.ashok.data.gen.exception.GenerationException;
import com.ashok.data.gen.model.Column;
import com.ashok.data.gen.model.ConfigModel;
import com.ashok.data.gen.util.XmlUtil;

public class ConfigXmlReader
{
	private Document configDom;

	public ConfigXmlReader(String configXmlPath) throws GenerationException
	{
		this.configDom = XmlUtil.getXmlDocument(configXmlPath);
	}

	public ConfigModel getConfiguration()
	{
		List<Column> columns = getColumns();
		int totalNoOfRecords = getTotalNoOfRecords();
		int recordPerFile = getRecordPerFile();
		String outputDir = getOutputDirectory();
		ConfigModel configModel = new ConfigModel();
		configModel.setColumns(columns);
		configModel.setOutputDirectory(outputDir);
		configModel.setTotalRecords(totalNoOfRecords);
		configModel.setTotalRecordsPerFile(recordPerFile);
		
		return configModel;
	}

	private String getOutputDirectory()
	{
		NodeList config = configDom
				.getElementsByTagName("Config");
	   return XmlUtil.getTextValue((Element)config.item(0), DataGenerationConstant.OUTPUTDIR);
	}

	private int getRecordPerFile()
	{
		NodeList config = configDom
				.getElementsByTagName("Config");
		return XmlUtil.getIntValue((Element)config.item(0), DataGenerationConstant.RECORDSPERFILE);
	}
	
	private int getTotalNoOfRecords()
	{
		NodeList config = configDom
				.getElementsByTagName("Config");
		return XmlUtil.getIntValue((Element)config.item(0), DataGenerationConstant.TOTALRECORDSIZE);
		
	}

	private List<Column> getColumns()
	{
		List<Column> columnList = new ArrayList<Column>();
		NodeList columns = configDom
				.getElementsByTagName(DataGenerationConstant.COLUMN);
		if (columns != null && columns.getLength() > 0)
		{
			for (int i = 0; i < columns.getLength(); i++)
			{

				// get the employee element
				Element el = (Element) columns.item(i);

				// get the Employee object
				Column column = XmlUtil.getColumn(el);

				// add it to list
				columnList.add(column);
			}
		}
		return columnList;
	}

}
