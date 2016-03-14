package kapil.kumar.MyCustomInputFormat;

import java.io.IOException;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

public class MyRecordReader extends RecordReader<MyKey,MyValue> 
{
	private MyKey key;
	private MyValue value;
	private LineRecordReader reader=new LineRecordReader();	//mean it will read line by line from input split

	@Override
	public void close() throws IOException {
		reader.close();
	}

	@Override
	public MyKey getCurrentKey() throws IOException, InterruptedException {
		return key;
	}

	@Override
	public MyValue getCurrentValue() throws IOException, InterruptedException {
		return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		return reader.getProgress();
	}

	@Override
	public void initialize(InputSplit arg0, TaskAttemptContext arg1)
			throws IOException, InterruptedException {
		reader.initialize(arg0, arg1);
		
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		return false;
//		reader.next
	}

}
