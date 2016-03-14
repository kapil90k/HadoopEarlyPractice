package kapil.kumar;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapR3AgraMapper  extends Mapper<LongWritable,Text,Text,IntWritable> {

	

	public void map(LongWritable key, Text value,
			Context context)
			throws IOException,InterruptedException {
	
			context.write(value, new IntWritable(1));
		}

		
	}
	
/*	public void map(LongWritable key, Text value,
			Context context)
			throws IOException,InterruptedException {
		count++;
		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line);

		while (tokenizer.hasMoreTokens()) {
			value.set(tokenizer.nextToken());
			context.write(value, new IntWritable(1));
		}

		System.out.println("value of count: "+count);
	}*/
	



