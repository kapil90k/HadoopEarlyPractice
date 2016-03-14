package kapil.kumar.airline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//question 1
/*public class AirlineMapper extends Mapper<LongWritable, Text, Text, Text> 
{
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
		String str=value.toString();
		String tokens[]=str.split(",");
		if(tokens[3].equals("India"))
		{
			context.write(new Text("City:	"+tokens[1]+", "+tokens[2]+"  "), new Text("Airport:	"+tokens[1]));
		}
	}
	
}
*/

//creating the dataset for airline with there ids. Output will be use as cache.
//use airline dataset as input
/*public class AirlineMapper extends Mapper<LongWritable, Text, Text, Text> 
{
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
		String str=value.toString();
		String tokens[]=str.split(",");
		context.write(new Text(tokens[0]),new Text(tokens[1]));
	}
}*/

//checking the cache dataset.



public class AirlineMapper extends Mapper<LongWritable, Text, Text, Text> 
{
	HashMap airlineId=new HashMap();
	
	public void setup(Context context) throws IOException
	{

		Path[] files = DistributedCache.getLocalCacheFiles(context.getConfiguration());
		
		
		for (Path p : files) {
			if (p.getName().equals("airline_ids")) {
				BufferedReader reader = new BufferedReader(new FileReader(p.toString()));
				String line = reader.readLine();
				while(line != null) {
					String[] tokens = line.split("\t");
					String airline_id = tokens[0];
					String airline_name = tokens[1];
					airlineId.put(airline_id, airline_name);
					line = reader.readLine();
				}
			}
		}
		if (airlineId.isEmpty()) {
			throw new IOException("Unable to load Abbrevation data.");
		}
	
	}
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
		String str=value.toString();
		String tokens[]=str.split(",");
		if (tokens[7].equals("0")) 
		{
			String airlineName=(String) airlineId.get(tokens[1]);
			context.write(new Text(airlineName),new Text(tokens[7]));
		}
	}
}