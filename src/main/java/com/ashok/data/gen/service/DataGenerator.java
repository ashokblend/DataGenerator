package com.ashok.data.gen.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ashok.data.gen.model.Column;
import com.ashok.data.gen.model.ConfigModel;

public class DataGenerator
{

	private ConfigModel configModel;
	private Map<String, Random> columnRandom = new HashMap<String, Random>();
	private BufferedWriter bw;
	private String outputPath;
	public DataGenerator(ConfigModel configModel)
	{
		this.configModel = configModel;
		intialise();
	}

	private void intialise()
	{
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		outputPath=this.configModel.getOutputDirectory()+File.separator+formatter.format(new Date());
		File outputDir = new File(outputPath);
		if(!outputDir.exists())
		{
			outputDir.mkdirs();
		}
		resetWriter();

	}

	public void generate()
	{
		try
		{
			int totalNoOfRecord = configModel.getTotalRecords();
			int recordsPerFile = configModel.getTotalRecordsPerFile();
			for (int i = 0; i < totalNoOfRecord; i++)
			{
				if (i == recordsPerFile)
				{
					resetWriter();
				}
				String record = createRecord(i);
				bw.write(record+"\n");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bw.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String createRecord(int recordCount)
	{
		StringBuffer record = new StringBuffer();
		List<Column> columns = configModel.getColumns();
		for (int i = 0; i < columns.size(); i++)
		{
			Column column = columns.get(i);
			Random random = columnRandom.get(column.getName());
			if (null == random)
			{
				random = new Random();
				columnRandom.put(column.getName(), random);
			}
			String randomData = getRandomData(random, column);
			record.append(randomData);
			if (i < columns.size() - 1)
			{
				record.append(",");
			}
		}
		return record.toString();
	}

	private String getRandomData(Random random, Column column)
	{
		switch (column.getDataType())
		{
		case STRING:
			return getRandomString(random, column);
		case DATE:
			return getRandomDate(random, column);
		case INTEGER:
			return getRandomInteger(random, column);
		case NUMERIC:
			return getRandomNumeric(random, column);
		default:
			return getRandomString(random, column);
		}

	}

	private String getRandomNumeric(Random random, Column column)
	{
		String randomInt=getRandomInteger(random, column);
		return randomInt.concat(".").concat(Double.toString(random.nextGaussian()));
	}

	private String getRandomInteger(Random random, Column column)
	{
		/*String rangeStr = column.getTemplate();
		int start = 0, end = 0;
		if (null != rangeStr)
		{
			start = Integer.parseInt(rangeStr.split("-")[0]);
			end = Integer.parseInt(rangeStr.split("-")[1]);
		}
		if (start > end)
		{
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) end - (long) start + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * random.nextDouble());
		int randomNumber = (int) (fraction + start);*/
		String startStr=column.getTemplate();
		int start=0;
		if(null!=startStr)
		{
			start=Integer.parseInt(startStr);
		}
		int randomNumber=random.nextInt(column.getCardinality())+start;
		return Integer.toString(randomNumber);
	}

	private String getRandomDate(Random random, Column column)
	{
		String format=column.getTemplate();
		SimpleDateFormat formatter=new SimpleDateFormat(format);
		long randomTime=System.currentTimeMillis()+random.nextInt(column.getCardinality());
		Date date = new Date(randomTime);
		return formatter.format(date);
	}

	private String getRandomString(Random random, Column column)
	{
		String template=column.getTemplate();
		return template+random.nextInt(column.getCardinality());
	}

	private void resetWriter()
	{
		try
		{
			if(null!=bw)
			{
				bw.flush();
				bw.close();
			}
			SimpleDateFormat formatter=new SimpleDateFormat("HHmmss");
			String fileName=formatter.format(new Date())+".csv";
			File outputFile=new File(outputPath,fileName);
			FileWriter fw= new FileWriter(outputFile);
			bw=new BufferedWriter(fw);
			System.out.println("Output:"+outputFile.getAbsolutePath());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
