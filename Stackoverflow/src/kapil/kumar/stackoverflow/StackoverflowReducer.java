package kapil.kumar.stackoverflow;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class StackoverflowReducer extends Reducer<Object,Object,IntWritable,IntWritable> 
{
	int count=0;
	public void reduce(Object key,Iterable<Object> values,Context context) throws IOException, InterruptedException
	{
		for (Object text : values) 
		{
			count++;
		}
		IntWritable okey=(IntWritable) key;
		context.write(okey,new IntWritable(count));
	}
}
	/*	String name=null;
		int count=0;
		String outputValue=null;
		for (Text value : values) 
		{
			String line=value.toString();
			String[] splits= line.split(" ");
			if (splits[0].equals("users: ")) 
			{
				name=splits[1];
			}
			if (splits[0].equals("post: "))
			{
				count++;
			}
			System.out.println("user id: "+key.get()+" and number of posts: "+count);
			outputValue=String.format("%d %d", key.get(),count);
		}
		context.write(new Text("kapil"+name),new Text(outputValue));
		}
	}*/
		/*if(key.toString().startsWith("posts "))
		{
			String newKey=key.toString().split(" ")[1];
			int sum=0;
			for (Object object : values) 
			{
				IntWritable i=(IntWritable)object;
				sum+=i.get();
			}
			postMap.put(newKey, sum);
		}
		if (key.toString().startsWith("users ")) 
		{
			String newKey=key.toString().split(" ")[1];
			String name=values.toString();
			userMap.put(newKey, name);
		}*/
		
//	}
//}
