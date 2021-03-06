package kapil.kumar.mr.chaining;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class ChainingJobsDriver 
{


	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException 
	{
		Configuration conf=new Configuration();
		Job job1=new Job(conf,"Calculate Average");
		
		job1.setJarByClass(ChainingJobsDriver.class);
		job1.setMapperClass(AverageMapper.class);
//		job1.setReducerClass(AverageReducer.class);
		
		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);
		
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(IntWritable.class);

		Path path0=new Path(args[0]);
		Path path1=new Path(args[1]);
		Path path2=new Path(args[2]);
		
		FileInputFormat.addInputPath(job1, path0);
		FileOutputFormat.setOutputPath(job1,path1);
		path1.getFileSystem(conf).delete(path1);
		
		/*
		 * 
		 * Driver 2 starts from here
		 * 
		 * 
		 * */
		
		
		Job job2=new Job(conf,"Calculate Average");
		
		job2.setJarByClass(ChainingJobsDriver.class);
		job2.setMapperClass(AsItIsMapper.class);
//		job1.setReducerClass(AverageReducer.class);
		
		job2.setInputFormatClass(TextInputFormat.class);
		job2.setOutputFormatClass(TextOutputFormat.class);
		
		job2.setOutputKeyClass(LongWritable.class);
		job2.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job2, path1);
		FileOutputFormat.setOutputPath(job2,path2);
		
		
		boolean b=job1.waitForCompletion(true);
		
		if (b)
		{
			job2.waitForCompletion(true);
		}
		else
			System.exit(1);
	}


}
