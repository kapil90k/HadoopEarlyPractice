package kapil.kumar.temperature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HotColdMapper extends Mapper<LongWritable, Text, Text,Text> 
{
	HashMap<String,String> monthMap=new HashMap<String,String>();
	public void setup(Context context) throws IOException
	{
		//URI[] cacheFiles = context.getCacheFiles();
		Path[] cacheFiles=DistributedCache.getLocalCacheFiles(context.getConfiguration());
		
		
		BufferedReader br=new BufferedReader(new FileReader(cacheFiles[0].toString()));
		
		String line=br.readLine();
		while (line!=null) {
			String[] tokens=line.split("\t");
			String fullName=tokens[0].trim();
			String shortName=tokens[1].trim();
			monthMap.put(fullName, shortName);
			line=br.readLine();
		}
		
	}
public void map(LongWritable key, Text value, Context context) throws InterruptedException, IOException
{
	String line=value.toString();
	StringTokenizer tokens=new StringTokenizer(line);
	String newKey=tokens.nextToken().trim();
	String cachedKey=monthMap.get(newKey);
	int max=0;
	int min=200;
	String newValues;
	while (tokens.hasMoreElements()) {
		int temp = Integer.parseInt(tokens.nextToken().trim());
		if (temp>max) {
			max=temp;
		}
		if(temp<min) {
			min=temp;
		}
	}
	String newVal="(max="+max+",min="+min+")";
	//System.out.println(newKey+" "+max);
	context.write(new Text(cachedKey),new Text(newVal));
	
}
	
}
