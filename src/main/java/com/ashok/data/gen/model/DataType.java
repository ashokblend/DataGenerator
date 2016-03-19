package com.ashok.data.gen.model;

public enum DataType
{
	STRING,DATE,INTEGER,NUMERIC;

	public static DataType getDataType(String dataType)
	{
		switch(dataType)
		{
		case "String" : return STRING;
		case "Integer": return INTEGER;
		case "Date": return DATE;
		case "Numeric": return NUMERIC;
		default: return STRING;
		}
		
	}

}
