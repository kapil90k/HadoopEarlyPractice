package kapil.kumar.postcount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

public class MyMapper extends Mapper<LongWritable, Text,Text,IntWritable>
{
	HashMap hm=new HashMap<String,Integer>();

	public void setup(Context context) throws IOException
	{
		Path[] cacheFile=DistributedCache.getLocalCacheFiles(context.getConfiguration());
		BufferedReader br=new BufferedReader(new FileReader(cacheFile[0].toString()));
		
		String line=br.readLine();
		while (line!=null) {
			String[] tokens=line.split("\t");
			String id=tokens[0].trim();
			int count=Integer.parseInt(tokens[1].trim());
			hm.put(id,count);
			line=br.readLine();
	}
	}
	public void map(LongWritable key,Text value,Context context) throws InterruptedException, IOException
	{
		String line=value.toString();
		String tokens[]=line.split(",");
		String id=tokens[0];
		int postCount=0;
			if((Integer) hm.get(id)!=null)
			{
				postCount=(Integer) hm.get(id);
			}				
		String name=tokens[2];
		
		String newKey=String.format("%s %s",id,name);
		context.write(new Text(newKey),new IntWritable(postCount));
	}
}
