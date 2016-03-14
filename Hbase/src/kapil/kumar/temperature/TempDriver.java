package kapil.kumar.temperature;

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


public class TempDriver 
{

	public static void main(String[] args) throws IOException,ClassNotFoundException,InterruptedException
	{
		//()
		Configuration conf=new Configuration();
		Job job=new Job(conf,"Temperature Calculate");
		
		job.setJarByClass(TempDriver.class);
		job.setMapperClass(TempMapper.class);
		//job.setReducerClass(TempReducer.class);
		
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		Path outputPath = new Path(args[1]);
		outputPath.getFileSystem(conf).delete(outputPath);
		
		job.waitForCompletion(true);
		
	}

}
