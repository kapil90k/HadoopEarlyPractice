package kapil.kumar.stackoverflow;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReduce extends Reducer<IntWritable,Text,Text,Text> 
{
	public void reduce(IntWritable key,Iterable<Text> values,Context context) throws InterruptedException,IOException
	{
		String name=null;
		int count=0;
		for (Text text : values) 
		{
			String text2=text.toString();
			if (text2.startsWith("users: "))
			{
				name=text2.substring(7, text2.length());
			}
			if (text2.startsWith("post: "))
			{
				count++;
			}
		}
		String id=String.format("%d ",key.get());
		String dNamePosts=String.format("%s  %d", name,count);
		context.write(new Text(id),new Text(dNamePosts));
		
	}
	
}
