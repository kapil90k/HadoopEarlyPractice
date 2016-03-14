package kapil.kumar.rj;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ReduceJoin {

	public static class CustMapper extends Mapper<Object,Text,Text,Text>
	{
		public void map(Object key,Text value,Context context) throws IOException,InterruptedException
		{
			String tokens[]=value.toString().split(",");
			context.write(new Text(tokens[0]), new Text("cust\t"+tokens[1]));
		}
	}
	
	public static class TxnMapper extends Mapper<Object,Text,Text,Text>
	{
		public void map(Object key,Text value,Context context) throws IOException,InterruptedException
		{
			String tokens[]=value.toString().split(",");
			context.write(new Text(tokens[2]), new Text("txns\t"+tokens[3]));
		}	
	}
	
	public static class CustTxnReducer extends Reducer<Text,Text,Text,Text>
	{
		public void reduce(Text key,Iterable<Text> values,Context context) throws InterruptedException,IOException
		{
			String name="";
			int count=0;
			double price=0.0;
			for(Text value:values)
			{
				String parts[]=value.toString().split("\t");
				if (parts[0].equals("cust"))
				{
					name=parts[1];
				}
				else if(parts[0].equals("txns"))
				{
					count++;
					price+=Float.parseFloat(parts[1]);
				}
			}
			String str=String.format("%d\t%f",count,price);
			context.write(new Text(name), new Text(str));
		}
}
public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException
{
	Configuration conf=new Configuration();
	Job job=new Job(conf,"Reduce side join");
	
	job.setJarByClass(ReduceJoin.class);
	job.setReducerClass(CustTxnReducer.class);
	
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(Text.class);
	
	MultipleInputs.addInputPath(job, new Path(args[0]),TextInputFormat.class, CustMapper.class);
	MultipleInputs.addInputPath(job, new Path(args[1]),TextInputFormat.class, TxnMapper.class);
	
	Path outputPath=new Path(args[2]);
	FileOutputFormat.setOutputPath(job,outputPath);
	
	outputPath.getFileSystem(conf).delete(outputPath);
	
	job.waitForCompletion(true);
	
	
}

}
