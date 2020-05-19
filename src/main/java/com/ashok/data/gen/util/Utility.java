package com.ashok.data.gen.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

import com.ashok.data.gen.constant.DataGenerationConstant;
import com.ashok.data.gen.sink.DataSink;
import com.ashok.data.gen.sink.FileSink;
import com.ashok.data.gen.sink.KafkaSink;

public class Utility {

	public static void closeStreams(Closeable... streams) {
		for (Closeable stream : streams) {
			closeStream(stream);
		}
	}

	public static void closeStream(Closeable stream) {
		if (null != stream) {
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

  public static DataSink getDataSink(Map<String, String> sinkInfo) {
    String sinkType = sinkInfo.get(DataGenerationConstant.SINK_TYPE);
    switch(sinkType) {
      case "filesink": return new FileSink(sinkInfo);
      case "kafkasink": return new KafkaSink(sinkInfo);
    }
    return null;
  }
}
