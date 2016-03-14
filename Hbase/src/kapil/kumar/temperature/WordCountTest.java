package kapil.kumar.temperature;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.*;
import org.junit.Test;

public class WordCountTest extends TestCase
{
	public static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>
	{
		public void map(LongWritable key,Text value,Context context)
		{
			String tokens[]=value.toString().split(",");
			String newKey=tokens[2];
			int newValue=tokens[2].length();
			try {
				context.write(new Text(newKey), new IntWritable(newValue));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//MapDriver<LongWritable, Text, Text, IntWritable> mrTest;
		
		/*public void setup()
		{
			WordCountTest abc=new WordCountTest();
			mrTest=MapDriver.newMapDriver();
		}*/
		@Test
		public void testMapper()
		{
			try {
				new MapDriver<LongWritable, Text, Text, IntWritable>().withMapper(new WordCountMapper()).withInput(new LongWritable(), new Text("1,sunday,abhay,holiday")).withOutput(new Text("abhay"), new IntWritable(5)).runTest();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
