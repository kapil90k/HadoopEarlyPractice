package kapil.kumar.average;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageReducer extends Reducer<Text,IntWritable,Text,IntWritable> 
{
	public void reduce(Text key,Iterable<IntWritable> values,Context context) throws InterruptedException,IOException
	{
		int count=0;
		int sum=0;
		int avg=0;
		for (IntWritable i: values) 
		{
			sum=sum+i.get();
			count++;
		}
		avg=sum/count;
		context.write(new Text("average:	"), new IntWritable(avg));
	}
}
