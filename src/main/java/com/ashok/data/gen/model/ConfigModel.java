package com.ashok.data.gen.model;

import java.util.List;
import java.util.Map;

public class ConfigModel
{
	private int totalRecords;
	
	private int totalRecordsPerFile;
	
	private String outputDirectory;
	
	private List<Column> columns;

	private Map<String, String> sinkInfo;

	public int getTotalRecords()
	{
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords)
	{
		this.totalRecords = totalRecords;
	}

	public int getTotalRecordsPerFile()
	{
		return totalRecordsPerFile;
	}

	public void setTotalRecordsPerFile(int totalRecordsPerFile)
	{
		this.totalRecordsPerFile = totalRecordsPerFile;
	}

	public String getOutputDirectory()
	{
		return outputDirectory;
	}

	public void setOutputDirectory(String outputDirectory)
	{
		this.outputDirectory = outputDirectory;
	}

	public List<Column> getColumns()
	{
		return columns;
	}

	public void setColumns(List<Column> columns)
	{
		this.columns = columns;
	}

    public Map<String, String> getSinkInfo() {
        return sinkInfo;
    }

    public void setSinkInfo(Map<String, String> sinkInfo) {
       this.sinkInfo = sinkInfo;
    }

}
