package kapil.kumar.custominput;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;


public class MyRecordReader extends RecordReader<MyKey,MyValue> {

	private MyKey key;
	private MyValue value;
	private LineRecordReader reader = new LineRecordReader();
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
	public void initialize(InputSplit is, TaskAttemptContext tac)
			throws IOException, InterruptedException {
		reader.initialize(is, tac);
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		
		boolean gotNextKeyValue = reader.nextKeyValue();		//when this method is getting call?
		if(gotNextKeyValue){
			if(key==null){
				key = new MyKey();
			}
			if(value == null){
				value = new MyValue();
			}
			Text line = reader.getCurrentValue();
			String[] tokens = line.toString().split("\t");
			key.setSensorType(new Text(tokens[0]));
			key.setTimestamp(new Text(tokens[1]));
			key.setStatus(new Text(tokens[2]));
			value.setValue1(new Text(tokens[3]));
			value.setValue2(new Text(tokens[4]));
		}
		else {
			key = null;
			value = null;
		}
		return gotNextKeyValue;
	}

}