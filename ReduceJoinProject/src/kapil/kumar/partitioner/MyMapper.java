package kapil.kumar.partitioner;

import java.io.IOException;

//import kapil.kumar.partitioner.MyDriver.Words;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable> 
{
	public void map(LongWritable key,Text value,Context context) throws InterruptedException,IOException
	{
		String str=value.toString();
		String parts[]=str.split(" ");
		for(String str2:parts)
		{
			context.write(new Text(str2), new IntWritable(1));
//			context.getCounter(Words.Total).increment(1);
//			context.getCounter(Words.Total).getName();
//			context.getCounter(Words.Total).getValue();
		}
	}
}
