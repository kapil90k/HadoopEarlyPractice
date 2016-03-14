package kapil.kumar.crime1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

@SuppressWarnings("unused")
public class CrimeOneReducer extends Reducer<Text, IntWritable, Text, IntWritable> 
{
	
	public void reduce(Text key, Iterable<IntWritable> value, Context context) throws IOException, InterruptedException
	{
		int sum=0;
		for(IntWritable finalValue:value)
		{
			sum+=finalValue.get();
		}
		System.out.println(key.toString()+" "+sum);
		context.write(key, new IntWritable(sum));
	}
}
