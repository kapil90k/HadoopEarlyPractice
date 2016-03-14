package kapil.kumar.crime1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CrimeOneMapper extends Mapper<LongWritable, Text, Text,IntWritable> 
{
	
	 /*Total Rapes in 2001 StateWise 
	 public void map(LongWritable key, Text value,Context context) throws InterruptedException, IOException
	{
		//()
		String line=value.toString();
		String[] tokens=line.split(",");
		if(tokens[1].substring(1, tokens[1].length()-1) .equalsIgnoreCase("rape"))
		{
			context.write(new Text(tokens[1]+"in state "+tokens[0]+" in"+tokens[2]),new IntWritable(Integer.parseInt(tokens[2])));
		}
	}*/
	
	//total number of crime statewise in 2001
	
	
	public void map(LongWritable key,Text value, Context context) throws InterruptedException,IOException
	{
		String line=value.toString();
		String[] tokens=line.split(",");
		
		//String newKey="Crime in "+tokens[0].substring(1, tokens[0].length()-1)+" in 2001  ";
		
		String newKey=tokens[0];
		int newValue=Integer.parseInt(tokens[2]);
		
			context.write(new Text(newKey), new IntWritable(newValue));
		
	}
	
}
