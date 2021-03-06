package kapil.kumar.mr.chaining;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AverageMapper extends Mapper<LongWritable,Text,Text,IntWritable>
{
	int num=0;
	int den=0;
	public void map(LongWritable key,Text value,Context context) throws InterruptedException,IOException
	{
		String line=value.toString();
		String tokens[]=line.split(",");
		for (String token: tokens) 
		{
			int i=Integer.parseInt(token);
			context.write(new Text("avg"), new IntWritable(i));
		}
	}
}
