package kapil.kumar.custominput;

import java.io.IOException;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class MyInputFormat extends FileInputFormat<MyKey, MyValue> {

	@Override
	public RecordReader<MyKey, MyValue> createRecordReader(InputSplit is,
			TaskAttemptContext tac) throws IOException, InterruptedException {
		MyRecordReader reader = new MyRecordReader();
		reader.initialize(is, tac);
		return reader;
	}
}
