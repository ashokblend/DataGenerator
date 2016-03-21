package com.ashok.data.gen;

import com.ashok.data.gen.model.ConfigModel;
import com.ashok.data.gen.service.ConfigXmlReader;
import com.ashok.data.gen.service.DataGenerator;

public class Main
{

	public static void main(String... args)
	{
		try
		{
			String configXmlPath = "D:\\github\\DataGenerator\\src\\main\\resources\\config.xml";
			if (args.length == 1)
			{
				configXmlPath = args[0];
			}
			ConfigXmlReader configXmlReader = new ConfigXmlReader(configXmlPath);
			ConfigModel configModel=configXmlReader.getConfiguration();
			DataGenerator dataGenerator = new DataGenerator(configModel);
			dataGenerator.generate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
