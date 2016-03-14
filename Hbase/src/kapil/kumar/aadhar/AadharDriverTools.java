package kapil.kumar.aadhar;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AadharDriverTools extends Configured implements Tool 
{

	
	public static void main(String[] args) throws Exception 
	{
		int exitCode=ToolRunner.run(new Configuration(), new AadharDriverTools(), args);
		System.exit(exitCode);
	}

	@Override
	public int run(String[] args) throws Exception {
		
		Configuration conf=getConf();
		Job job=new Job(conf,"Calculate aadhar");
		
		
		job.setJarByClass(AadharDriverTools.class);
		job.setMapperClass(AadharMapper.class);
		job.setReducerClass(AadharReducer.class);
		job.setCombinerClass(AadharReducer.class);
		job.setNumReduceTasks(0);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job,new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		Path outputPath=new Path(args[1]);
		outputPath.getFileSystem(conf).delete(outputPath);
		
		int returnStatus=job.waitForCompletion(true)?0:1;
		return returnStatus;
	}

}
