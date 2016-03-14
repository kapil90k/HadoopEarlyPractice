package kapil.kumar.images;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

public class ImageDuplicateDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException 
	{
		Configuration conf=new Configuration();
		Job job=new Job(conf, "Discard Duplicate Images");
		
		job.setMapperClass(ImageDuplicateMapper.class);
		job.setJarByClass(ImageDuplicateDriver.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BytesWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job,new Path(args[1]));
		
		Path outputPath=new Path(args[1]);
		outputPath.getFileSystem(conf).delete(outputPath);
		
		job.waitForCompletion(true);
		
	}

}
