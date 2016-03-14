package kapil.kumar.health;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class HealthDriver {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException 
	{


		Configuration conf= new Configuration();
		Job job=new Job(conf,"CrimeOne");
		
		job.setJarByClass(HealthDriver.class);
		job.setMapperClass(HealthMapper.class);
		//job.setReducerClass(CrimeOneReducer.class);
		
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job,new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		Path outputPath=new Path(args[1]);
		outputPath.getFileSystem(conf).delete(outputPath);
		
		System.exit(job.waitForCompletion(true)?0:1);
	

	
	}

}
