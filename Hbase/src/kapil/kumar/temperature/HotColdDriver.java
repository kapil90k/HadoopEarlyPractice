package kapil.kumar.temperature;

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

public class HotColdDriver {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException 
	{
		Configuration conf= new Configuration();
		Job job=new Job(conf,"Calculate Hot Day");
		
		//DistributedCache.addCacheFile(new URI("hdfs://localhost:8020/mapr/practice/HotColdCache.txt"), job.getConfiguration());
		DistributedCache.addCacheFile(new URI("/mapr/practice/HotColdCache.txt"), job.getConfiguration());
		//DistributedCache.addCacheFile(uri, conf)
		
		
		
		
		job.setJarByClass(HotColdDriver.class);
		job.setMapperClass(HotColdMapper.class);
		//job.setReducerClass(HotColdReducer.class);
		
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		//job.setOutputFormatClass(SequenceFileOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job,new Path(args[0]));
		FileInputFormat.addInputPath(job,new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		
		Path outputPath=new Path(args[2]);
		outputPath.getFileSystem(conf).delete(outputPath);
		
		System.exit(job.waitForCompletion(true)?0:1);
		
	}

}
