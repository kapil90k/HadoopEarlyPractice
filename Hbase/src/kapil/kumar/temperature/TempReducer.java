package kapil.kumar.temperature;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TempReducer extends Reducer<Text,IntWritable,Text,IntWritable>
{
	
	public void reducer(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
	{
		int sum=0;
		for(IntWritable x:values)
		{
			sum=sum+x.get();
		}
		context.write(key,new IntWritable(sum));
	}
}
