package kapil.kumar.mr.chaining;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AsItIsMapper extends Mapper<LongWritable, Text, LongWritable, Text>
{	
	public void map(LongWritable key,Text value,Context context)
	{
		try {
			context.write(key, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
