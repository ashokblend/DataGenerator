package com.ashok.data.gen.model;

public enum DataType
{
	STRING,DATE,INTEGER,NUMERIC,IP,TIMESTAMP;

	public static DataType getDataType(String dataType)
	{
		switch(dataType.toLowerCase())
		{
		case "string" : return STRING;
		case "integer": return INTEGER;
		case "date": return DATE;
		case "numeric": return NUMERIC;
		case "timestamp": return TIMESTAMP;
		case "ip" : return IP;
		default: return STRING;
		}
		
	}

}
