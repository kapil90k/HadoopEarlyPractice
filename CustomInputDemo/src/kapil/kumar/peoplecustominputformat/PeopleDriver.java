package kapil.kumar.peoplecustominputformat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


/*find the area-wise population
 * ex:
 * population of gopalpura agra:	5
 * population of pimpley saudagar pune:	10
 * 
 */


public class PeopleDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException 
	{
		Configuration conf=new Configuration();
		Job job=new Job(conf, "Custom format for people");
		
		job.setJarByClass(PeopleDriver.class);
		job.setMapperClass(PeopleMapper.class);
		job.setNumReduceTasks(0);
		job.setInputFormatClass(PeopleInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		Path outputPath=new Path(args[1]);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, outputPath);
		outputPath.getFileSystem(conf).delete(outputPath);
		
		job.waitForCompletion(true);
		
		
	}

}
