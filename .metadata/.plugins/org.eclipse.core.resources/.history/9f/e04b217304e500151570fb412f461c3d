package kapil.kumar.waverage;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AverageMapper extends Mapper<LongWritable,Text,IntWritable,IntWritable>
{
	int num=0;
	int den=0;
	public void map(LongWritable key,Text value,Context context)
	{
		String str=value.toString();
		String tokens[]=str.split("\t");
		int n=Integer.parseInt(tokens[0]);
		int w=Integer.parseInt(tokens[1]);
		num+=n*w;
		den+=n;
	}
}
