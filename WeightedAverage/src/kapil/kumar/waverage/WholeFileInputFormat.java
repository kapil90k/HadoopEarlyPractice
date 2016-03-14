package kapil.kumar.waverage;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class WholeFileInputFormat extends FileInputFormat<NullWritable,ByteWritable> 
{

	@Override
	protected boolean isSplitable(JobContext context, Path filename) 
	{
		return false;
	}
	
	@Override
	public RecordReader<NullWritable,ByteWritable> createRecordReader(InputSplit split,
			TaskAttemptContext context) throws IOException, InterruptedException {
		WholeFileRecordReader reader=new WholeFileRecordReader();
		reader.initialize(split, context);
		return reader;
	}

}
