package com.ashok.data.gen.sink;

public interface DataSink {

	public void write(String line)  throws Exception;
	
	public void close();
}
