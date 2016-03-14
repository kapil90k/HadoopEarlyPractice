package kapil.kumar.airline;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class AirlineDriver 
{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException 
	{
		Configuration conf= new Configuration();
		Job job=new Job(conf,"Airline");
		
		job.setJarByClass(AirlineDriver.class);
		job.setMapperClass(AirlineMapper.class);
		//job.setReducerClass(AirlineReducer.class);
		DistributedCache.addCacheFile(new URI("/airline/datasets/airline_ids"), job.getConfiguration());
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job,new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		Path outputPath=new Path(args[1]);
		outputPath.getFileSystem(conf).delete(outputPath);
		
		System.exit(job.waitForCompletion(true)?0:1);
	}

}
