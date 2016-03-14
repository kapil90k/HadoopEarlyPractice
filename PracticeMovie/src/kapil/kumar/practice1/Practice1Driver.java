package kapil.kumar.practice1;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Practice1Driver {

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
		
		conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", "->");
		
		job.setJarByClass(Practice1Driver.class);
		job.setMapperClass(Practice1Mapper.class);
		//job.setReducerClass(CrimeOneReducer.class);
		
		
		job.setInputFormatClass(KeyValueTextInputFormat.class);
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
