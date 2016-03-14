package kapil.kumar.aadhar;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AadharMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{

	/*public void map(LongWritable key, Text value,Context context) throws InterruptedException, IOException
	{
		String line=value.toString();
		StringTokenizer tokens=new StringTokenizer(line, "\t");
		//String newKey=tokens.nextToken().trim();
		context.write(new Text(tokens.nextToken().trim()), new Text(tokens.nextToken().trim()));
	}*/
	
	public void map(LongWritable key, Text value,Context context) throws InterruptedException, IOException
	{
		String line=value.toString();
		String[] tokens=line.split("\t");
		String newKey=null;
		int newValue=0;
		for(int i=0;i<tokens.length;i++)
		{
			if(i==0){
			newKey=tokens[i];}
			if(i==2)
				newValue=Integer.parseInt(tokens[2]);		}
		//String newKey=tokens.nextToken().trim();
		context.write(new Text(newKey), new IntWritable(newValue));
	}
	
}
