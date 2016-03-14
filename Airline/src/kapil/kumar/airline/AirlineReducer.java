package kapil.kumar.airline;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AirlineReducer extends Reducer<Text,IntWritable,Text,IntWritable> 
{
	public void reduce(Text key, Iterable<IntWritable> value, Context context) throws InterruptedException, IOException
	{
		int sum=0;
		for(IntWritable approve:value)
		{
			sum+=approve.get();
		}
		String newKey="num of airport in "+key.toString()+" are: ";
		context.write(new Text(newKey), new IntWritable(sum));
	}
}
