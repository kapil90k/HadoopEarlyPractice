package kapil.kumar.peoplecustominputformat;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PeopleMapper extends Mapper<Locality,PersonDetail,Text,Text> 
{
	public void map(Locality key,PersonDetail value,Context context) throws InterruptedException,IOException
	{
		String newkey=key.getArea().toString()+" * "+key.getCity().toString()+" * "+key.getState().toString();
		String newValue=value.getId().toString()+" : "+value.getfName()+" : "+value.getlName().toString()+" : "+value.getOccupation().toString();
		context.write(new Text(newkey), new Text(newValue));
	}
}
