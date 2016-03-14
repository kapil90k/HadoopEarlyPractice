package kapil.kumar.custominput;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MyFile {

//	public enum Check
//	{
//		Total;
//	}
  public static void main(String[] args) 
                  throws IOException, ClassNotFoundException, InterruptedException {
    if (args.length != 2) {
      System.err.println("Usage: <input path> <output path>");
      System.exit(-1);
    }
    
    Configuration conf=new Configuration();
    Job job = new Job(conf,"custom input format");
    job.setJarByClass(MyFile.class);
    job.setJobName("CustomTest");
    job.setNumReduceTasks(0);
    job.setMapperClass(MyMapper.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);
    job.setInputFormatClass(MyInputFormat.class);
    
    Path outputPath=new Path(args[1]);
    
    MyInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, outputPath);
    
    outputPath.getFileSystem(conf).delete(outputPath);
    
    job.waitForCompletion(true);
  }
}