package kapil.kumar.temperature;


import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TempMapper extends Mapper<LongWritable	, Text, Text, IntWritable> 
{
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		//()
		
		String line=value.toString();
		StringTokenizer tokens=new StringTokenizer(line," ");
		while(tokens.hasMoreTokens())
		{
		}
		String[] splits=line.split(" ");
		for(int i=0;i<splits.length;i++)
		{
			context.write(new Text(splits[i]), new IntWritable(1));
		}
	}
}
