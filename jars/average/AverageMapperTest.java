package kapil.kumar.average;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class AverageMapperTest extends TestCase
{
	MyAverageMapper mapper=new MyAverageMapper();
	MyAverageReducer reducer=new MyAverageReducer();
	
	MapDriver<LongWritable,Text,Text,IntWritable> mapDriver=MapDriver.newMapDriver(new MyAverageMapper());
	ReduceDriver<Text, IntWritable, Text,IntWritable> reduceDriver=ReduceDriver.newReduceDriver(new MyAverageReducer());
	MapReduceDriver<LongWritable,Text,Text,IntWritable,Text,IntWritable> mrDriver=MapReduceDriver.newMapReduceDriver(mapper, reducer);
	
	public static class MyAverageMapper extends Mapper<LongWritable,Text,Text,IntWritable>
	{
		public void map(LongWritable key,Text value,Context context) throws InterruptedException,IOException
		{
			String line=value.toString();
			String tokens[]=line.split(",");
			for (String str : tokens) 
			{
				int i=Integer.parseInt(str);
				context.write(new Text("avg"),new IntWritable(i));
			}
			/*String line=value.toString();
			String tokens[]=line.split(",");
			context.write(new Text("avg"),new IntWritable(Integer.parseInt(tokens[9])));*/
		}
	}
	
	public static class MyAverageReducer extends Reducer<Text,IntWritable,Text,IntWritable>
	{
		public void reduce(Text key,Iterable<IntWritable> values,Context context)
		{
			int sum=0;
			int count=0;
			int avg=0;
			for (IntWritable i : values) 
			{
				sum+=i.get();
				count++;
			}
			avg=sum/count;
			try {
				context.write(new Text("avg: "),new IntWritable(avg));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*int count=0;
			for (IntWritable i: values) 
			{
				if (i.get()==5) {
					try {
						context.write(new Text("avg"),new IntWritable(5));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
					count++;*/
			}
	}
	
		
		@Test
		public void testMapper()
		{
			try {
				mapDriver.withInput(new LongWritable(),new Text("1,2,3,4,5,6,7,8,9,10"));
				mapDriver.withOutput(new Text("avg"), new IntWritable(10));
				mapDriver.runTest();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	/*	@Test
		public void testReducer()
		{
			List<IntWritable> iList=ImmutableList.of(new IntWritable(5),new IntWritable(6),new IntWritable(7),new IntWritable(8));
//			ImmutableList<IntWritable> iList=ImmutableList.of(new IntWritable(5),new IntWritable(6),new IntWritable(7),new IntWritable(8));
			reduceDriver.withInput(new Text("avg"),iList);
			reduceDriver.withOutput(new Text("avg"), new IntWritable(5));
			try {
				reduceDriver.runTest();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		/*@Test
		public void testMapReducer()
		{
			mrDriver.withInput(new LongWritable(), new Text("1,4,2,3,4,5,4560,6,7,8,9,10"));
			mrDriver.withOutput(new Text("avg: "), new IntWritable(5));
			try {
				mrDriver.runTest();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
}
