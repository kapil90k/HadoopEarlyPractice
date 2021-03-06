package kapil.kumar.weightedaverage;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageReducer extends Reducer<Text,Text,Text,Text> 
{
	public void reduce(Text key,Iterable<Text> values,Context context) throws InterruptedException,IOException
	{
		int num=0;
		int den=0;
		float wavg=0;
		for (Object value : values)
		{
			String line=value.toString();
			String tokens[]=line.split("\t");
			int n=Integer.parseInt(tokens[0]);
			int w=Integer.parseInt(tokens[1]);
			num+=n*w;
			den+=n;
		}
		wavg=num/den;
		String wavg2=String.format("%s", wavg);
		context.write(new Text("weighted average is:	"),new Text(wavg2));//, new IntWritable(wavg));
		
		/*int count=0;
		int sum=0;
		int avg=0;
		for (IntWritable i: values) 
		{
			sum=sum+i.get();
			count++;
		}
		avg=sum/count;
		context.write(new Text("average:	"), new IntWritable(avg));*/
	}
}
