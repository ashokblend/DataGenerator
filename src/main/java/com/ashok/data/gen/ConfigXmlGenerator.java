package com.ashok.data.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ConfigXmlGenerator {
	
	public static void main(String[] args) {
		int dimCount = 2500;
		String configXmlPath="/Users/ashok.kumar/github/DataGenerator/src/main/resources/config_custom.xml";
		String outputDir="/Users/ashok.kumar/github/workspace/spark/input/orc/10/";
		int totalRecords = 10;
		int recordsPerFile= 10;
		//CREATE TABLE temp (imei string,protocol string,mac string, city string, sales int) USING com.databricks.spark.csv OPTIONS (path "/Users/ashok.kumar/github/workspace/spark/input/orc/20190411/")
		StringBuffer ctable = new StringBuffer();
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<Config>\n");
		sb.append(" <Columns>\n");
		//for table
		ctable.append("create table temp(");
		for(int i=1; i<=dimCount ;i++) {
			int cardinality =(i+1)*10000;
			String dimName="dimension"+i;
			String dataType = getDataType(i);
			String template=getTemplate(i);
			
			//for table
			ctable.append(dimName +" "+dataType);
			if(i < (dimCount)) {
				ctable.append(",");
			}
			
			sb.append("  <Column>\n");
			
			sb.append("    <cardinality>"+cardinality+"</cardinality>\n");
			sb.append("    <name>"+dimName+"</name>\n");
			sb.append("    <datatype>"+dataType+"</datatype>\n");
			sb.append("    <template>"+template+"</template>\n");
			  
			sb.append("  </Column>\n");
		}
		//for table
		ctable.append(")");
		
		sb.append(" </Columns>\n");
		sb.append(" <OutputDir>"+outputDir+"</OutputDir>\n");
		sb.append(" <TotalRecordSize>"+totalRecords+"</TotalRecordSize>\n");
		sb.append(" <RecordsPerFile>"+recordsPerFile+"</RecordsPerFile>\n");
		 
		sb.append("</Config>");
		writeConfigXml(sb, configXmlPath);
		System.out.println(ctable.toString());
	}

	private static void writeConfigXml(StringBuffer sb, String configXmlPath) {
		try {
			FileWriter fw = new FileWriter(new File(configXmlPath));
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.flush();
			fw.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static String getTemplate(int i) {
		if(i%7==0) {
			return "123";
		} else if(i%23==0){
			return "2334344334343";
		} else {
			return "str";
		}
	}

	private static String getDataType(int i) {
		if(i%7==0) {
			return "int";
		} else if(i%23==0){
			return "long";
		} else {
			return "String";
		}
	}

}
