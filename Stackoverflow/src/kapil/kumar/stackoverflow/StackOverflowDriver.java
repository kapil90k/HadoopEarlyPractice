package kapil.kumar.stackoverflow;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class StackOverflowDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException 
	{
		Configuration conf =new Configuration();
		Job job=new Job(conf, "Stackoverflow Statistics");
		
		job.setJarByClass(StackOverflowDriver.class);
		job.setReducerClass(MyReduce.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		Path path0=new Path(args[0]);
		Path path1=new Path(args[1]);
		Path path2=new Path(args[2]);
		
		//FileInputFormat.addInputPath(job,path1);
		
		MultipleInputs.addInputPath(job, path0, TextInputFormat.class, UserMapper.class);
		MultipleInputs.addInputPath(job, path1, TextInputFormat.class, PostsMapper.class);
		
		
		FileOutputFormat.setOutputPath(job, path2);
		
		path2.getFileSystem(conf).delete(path2);
		job.waitForCompletion(true);
		
	}

}
