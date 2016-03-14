package kapil.kumar.health;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HealthMapper extends Mapper<LongWritable,Text,LongWritable,Text> 
{
	int i=0;
	public void map(LongWritable key,Text value,Context context) throws InterruptedException,IOException
	{
		context.write(key,value);
	}
}
