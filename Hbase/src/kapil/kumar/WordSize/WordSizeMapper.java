package kapil.kumar.WordSize;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordSizeMapper extends Mapper<LongWritable, Text,Text,IntWritable> 
{
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String line=value.toString();
		StringTokenizer tokens=new StringTokenizer(line, " ");
		while(tokens.hasMoreTokens())
		{
			String name=tokens.nextToken();
			int name_len=name.length();
			context.write(new Text(name),new IntWritable(name_len));
		}
	}

}
