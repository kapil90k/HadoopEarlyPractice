package kapil.kumar.travel;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TravelMapper extends Mapper<LongWritable,Text,Text,Text> 
{
	public void map(LongWritable key,Text value,Context context) throws InterruptedException,IOException
	{
		String line=value.toString();
		String []tokens=line.split("\t");
		String str =","+tokens[1]+",";
		for (int i = 2; i < tokens.length; i++) 
		{
			str+=String.format("%s,",tokens[i]);
		}
		context.write(new Text(tokens[0]), new Text(str));
	}
}
