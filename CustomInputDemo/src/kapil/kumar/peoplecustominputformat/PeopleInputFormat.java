package kapil.kumar.peoplecustominputformat;

import java.io.IOException;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class PeopleInputFormat extends FileInputFormat<Locality, PersonDetail> 
{

	@Override
	public RecordReader<Locality, PersonDetail> createRecordReader(InputSplit in,
			TaskAttemptContext tac) throws IOException, InterruptedException {
		PeopleRecordReader reader=new PeopleRecordReader();
		reader.initialize(in,tac);
		return reader;
	}

}
