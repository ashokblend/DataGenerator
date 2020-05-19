package com.ashok.data.gen.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
		Map<String, String> sinkInfo = getSinkInfo();
		String outputDir = getOutputDirectory();
		ConfigModel configModel = new ConfigModel();
		configModel.setColumns(columns);
		configModel.setOutputDirectory(outputDir);
		configModel.setTotalRecords(totalNoOfRecords);
		configModel.setSinkInfo(sinkInfo);
		return configModel;
	}

	private Map<String, String> getSinkInfo() 
	{
	  Map<String, String> sinkInfo = new HashMap<>();
	  NodeList columns = configDom
          .getElementsByTagName(DataGenerationConstant.SINK);
	  String sinkType = XmlUtil.getTextValue((Element)columns.item(0), DataGenerationConstant.SINK_TYPE);
	  sinkInfo.put(DataGenerationConstant.SINK_TYPE, sinkType);
	  NodeList sink = configDom
          .getElementsByTagName(sinkType);
	  
	  Element el = (Element) sink.item(0);
	  NodeList sinkAttr = el.getChildNodes();
      for(int i=0;i< sinkAttr.getLength(); i++) {
        if(sinkAttr.item(i).getNodeType()==Node.ELEMENT_NODE) {
          String key = sinkAttr.item(i).getNodeName();
          String value = sinkAttr.item(i).getFirstChild().getNodeValue();
          sinkInfo.put(key, value);
        }
      }
	  
	  return sinkInfo;
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
