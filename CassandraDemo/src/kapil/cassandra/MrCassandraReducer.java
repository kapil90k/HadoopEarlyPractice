package kapil.cassandra;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MrCassandraReducer extends Reducer<Text,IntWritable,Text,IntWritable>
{
	public void reduce(Text key,Iterable<IntWritable> values,Context context) throws InterruptedException,IOException
	{
		int sum = 0;
		for (IntWritable val : values) {
		sum += val.get();
		}
		context.write(key, new IntWritable(sum));
	}
}
