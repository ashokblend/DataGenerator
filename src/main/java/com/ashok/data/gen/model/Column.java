package com.ashok.data.gen.model;

public class Column
{
	private String name;
	
	private DataType dataType;
	
	private int cardinality;
	
	private String template;

	public Column(String name, int cardinality, DataType dataType,
			String template)
	{
		this.name = name;
		this.cardinality = cardinality;
		this.dataType = dataType;
		this.template = template;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public DataType getDataType()
	{
		return dataType;
	}

	public void setDataType(DataType dataType)
	{
		this.dataType = dataType;
	}

	public int getCardinality()
	{
		return cardinality;
	}

	public void setCardinality(int cardinality)
	{
		this.cardinality = cardinality;
	}

	public String getTemplate()
	{
		return template;
	}

	public void setTemplate(String template)
	{
		this.template = template;
	}
	
	

}
