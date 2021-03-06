package kapil.kumar.weightedaverage;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class AverageDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException 
	{
		Configuration conf=new Configuration();
		Job job=new Job(conf,"Calculate Average");
		
		job.setJarByClass(AverageDriver.class);
		job.setMapperClass(AverageMapper.class);
//		job.setReducerClass(AverageReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		Path path0=new Path(args[0]);
		Path path1=new Path(args[1]);
		Path path2=new Path(args[2]);
		
		
		FileInputFormat.addInputPath(job, path0);
		FileInputFormat.addInputPath(job, path1);
		FileOutputFormat.setOutputPath(job,path2);

		//path2.getFileSystem(conf).delete(path2);
		
		job.waitForCompletion(true);
	}

}
