package com.ashok.data.gen.sink;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.ashok.data.gen.constant.DataGenerationConstant;
import com.ashok.data.gen.util.Utility;

public class FileSink implements DataSink {

  private BufferedWriter bw;

  private String outputPath;

  private int recordsPerFile;

  private int recordCounter;

  public FileSink(Map<String, String> sinkInfo) {
    this.recordsPerFile = Integer.parseInt(sinkInfo.get(DataGenerationConstant.RECORDSPERFILE));
    init(sinkInfo.get(DataGenerationConstant.OUTPUTDIR));
    
  }
  public void init(String outDir) {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    outputPath = outDir + File.separator
        + formatter.format(new Date());
    File outputDir = new File(outputPath);
    if (!outputDir.exists()) {
      outputDir.mkdirs();
    }
    resetWriter();

  }

  private void resetWriter() {
    try {
      close();
      SimpleDateFormat formatter = new SimpleDateFormat("HHmmssSSS");
      String fileName = formatter.format(new Date())
          + DataGenerationConstant.EXT;
      File outputFile = new File(outputPath, fileName);
      FileWriter fw = new FileWriter(outputFile);
      bw = new BufferedWriter(fw);
      System.out.println("Output:" + outputFile.getAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void write(String line) throws Exception {
    try {
      if (recordCounter == recordsPerFile) {
        resetWriter();
        recordCounter = 0;
      }
      recordCounter++;
      bw.write(line);
      bw.write("\n");
    } catch (IOException e) {
      throw new Exception(e);
    }
  }

  @Override
  public void close() {
    try {
      if(null != bw) {
        bw.flush();
        Utility.closeStream(bw);
      }
    } catch(Exception e) {
      System.err.println("Error in closing file writer");
    }
    
  }

}
