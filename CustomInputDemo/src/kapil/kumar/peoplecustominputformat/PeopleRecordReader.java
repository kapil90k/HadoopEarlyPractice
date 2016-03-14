package kapil.kumar.peoplecustominputformat;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

public class PeopleRecordReader extends RecordReader<Locality,PersonDetail> 
{
	private Locality key;
	private PersonDetail value;
	private LineRecordReader reader=new LineRecordReader();

	@Override
	public void close() throws IOException {
	reader.close();	
	}

	@Override
	public Locality getCurrentKey() throws IOException, InterruptedException {
		return key;
	}

	@Override
	public PersonDetail getCurrentValue() throws IOException,
			InterruptedException {
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
		boolean gotNextKeyValue=reader.nextKeyValue();
		if (gotNextKeyValue)
		{
			if (key==null)
			{
				key=new Locality();
			}
			if (value==null)
			{
				value=new PersonDetail();
			}
			Text line=reader.getCurrentValue();
			String str[]=line.toString().split(",");
			key.setArea(new Text(str[3]));
			key.setCity(new Text(str[4]));
			key.setState(new Text(str[5]));
			value.setId(new Text(str[0]));
			value.setfName(new Text(str[1]));
			value.setlName(new Text(str[2]));
			value.setOccupation(new Text(str[6]));
		}
		else
		{
			key=null;
			value=null;
		}
		return gotNextKeyValue;
	}

}
