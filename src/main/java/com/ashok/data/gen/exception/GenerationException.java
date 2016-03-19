package com.ashok.data.gen.exception;

public class GenerationException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GenerationException(String message,Exception e)
	{
		super(message,e);
	}
	
	public GenerationException(String message)
	{
		super(message);
	}
	public GenerationException(Exception e)
	{
		super(e);
	}
}
