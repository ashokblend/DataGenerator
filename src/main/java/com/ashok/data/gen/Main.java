package com.ashok.data.gen;

import java.util.Map;

import com.ashok.data.gen.model.ConfigModel;
import com.ashok.data.gen.service.ConfigXmlReader;
import com.ashok.data.gen.service.DataGenerator;
import com.ashok.data.gen.sink.DataSink;
import com.ashok.data.gen.util.Utility;

public class Main
{

	public static void main(String... args)
	{
		try
		{
			String configXmlPath = "/home/ashok/github/DataGenerator/src/main/resources/config.xml";
			if (args.length == 1)
			{
				configXmlPath = args[0];
			}
			ConfigXmlReader configXmlReader = new ConfigXmlReader(configXmlPath);
			ConfigModel configModel=configXmlReader.getConfiguration();
			Map<String, String> sinkInfo =configModel.getSinkInfo();
			DataSink dataSink = Utility.getDataSink(sinkInfo);
			DataGenerator dataGenerator = new DataGenerator(configModel, dataSink);
			dataGenerator.generate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
