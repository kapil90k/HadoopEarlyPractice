package kapil.kumar.aadhar;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AadharReducer extends Reducer<Text, IntWritable, Text, IntWritable>
{
	public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException,InterruptedException
	{
		int sum=0;
		for(IntWritable approve:values)
		{
			sum+=approve.get();
		}
		
		context.write(new Text(key),new IntWritable (sum));
		
	}

}
