package kapil.kumar.postcount;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class PostCountDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException 
	{

		Configuration conf =new Configuration();
		Job job=new Job(conf, "alksf");
		
		job.setJarByClass(PostCountDriver.class);
		job.setMapperClass(MyMapper.class);
//		job.setReducerClass(MyReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		DistributedCache.addCacheFile(new URI("/stackoverflow/datasets/postcount.txt"), job.getConfiguration());
		
		
		Path path0=new Path(args[0]);
		Path path1=new Path(args[1]);
		
		FileInputFormat.addInputPath(job, path0);
		FileOutputFormat.setOutputPath(job, path1);
		
		
		path1.getFileSystem(conf).delete(path1);
		job.waitForCompletion(true);
		
	
	}

}
