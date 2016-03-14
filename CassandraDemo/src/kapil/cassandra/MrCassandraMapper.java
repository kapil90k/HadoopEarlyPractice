package kapil.cassandra;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MrCassandraMapper extends Mapper<LongWritable,Text,Text,IntWritable> 
{
	CassandraHelper cclient=new CassandraHelper();
	public void map(LongWritable key,Text value,Context context) throws InterruptedException, IOException
	{
		
		String line=value.toString();
		context.write(new Text(line), new IntWritable(1));
		cclient.addKey2(line);
	}
	
	/*public void setup(Context context)
	{
		cclient.createConnection("127.0.0.1");
	}
	
	public void cleanup(Context context)
	{
		//cclient.closeConnection();
	}*/
}
