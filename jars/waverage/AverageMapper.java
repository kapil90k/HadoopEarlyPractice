package kapil.kumar.weightedaverage;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AverageMapper extends Mapper<LongWritable,Text,Text,Text>
{
	public void map(LongWritable key,Text value,Context context) throws InterruptedException,IOException
	{
		String line=value.toString();
		String tokens[]=line.split("\t");
		String n=tokens[0];
		String w=tokens[1];
		String newValue=String.format("%s\t%s", n,w);

		context.write(new Text("avg"), new Text(newValue));
	}
}
