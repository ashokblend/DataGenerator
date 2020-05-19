package com.ashok.data.gen.constant;

public interface DataGenerationConstant
{
	public static final String TOTALRECORDSIZE="TotalRecordSize";
	
	public static final String COLUMN_CARDINALITY="cardinality";
	
	public static final String COLUMN_NAME="name";
	
	public static final String COLUMN_DATA_TYPE="datatype";
	
	public static final String COLUMN_TEMPLATE="template";
	
	public static final String COLUMN="Column";
	
	public static final String DELIMITER="\t";
	
	public static final String EXT=".tsv";
	
	public static final String SINK = "Sink";
	
	public static final String SINK_TYPE="sinktype";
	
	//filesink
    public static final String RECORDSPERFILE="RecordsPerFile";
    public static final String OUTPUTDIR="OutputDir";
    
	//kafka sink
    public static final String TOPIC="topic";
    public static final String BOOTSTRAP_SERVER="bootStrapServer";
    public static final String ACK = "ack";
    public static final String RETRY="retry";
    public static final String BATCH_SIZE="batchSize";

}
