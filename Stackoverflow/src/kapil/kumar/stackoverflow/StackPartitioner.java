package kapil.kumar.stackoverflow;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class StackPartitioner extends Partitioner<IntWritable,Text> 
{

	@Override
	public int getPartition(IntWritable key, Text value, int arg2) 
	{
		return 0;
	}

}
