package kapil.kumar.partitioner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartitioner extends Partitioner<Text,IntWritable> 
{

	@Override
	public int getPartition(Text key, IntWritable value, int numPartitioner) 
	{
		String distkey=key.toString();
		if (distkey.length()<3)
		{
			return 0;
		}
		else if (distkey.length()==3)
		{
			return 1;
		}
		else return 2;
	}

}
