package kapil.kumar.stackoverflow;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PostsMapper extends Mapper<LongWritable,Text,IntWritable,Text> 
{
	public void map(LongWritable key, Text value,Context context) throws InterruptedException,IOException
	{
		String line=value.toString();
		String[] splits=line.split(",");
		int id=Integer.parseInt(splits[5]);
		String postId=splits[0];
		context.write(new IntWritable(id),new Text("post: "+postId));
	}
}
