package kapil.kumar.practice1;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Practice1Mapper extends Mapper<Text,Text,Text,Text> 
{
public void map(Text key,Text value,Context context) throws IOException, InterruptedException
{
	String newKey="key= "+key.toString();
	String valueList[]=value.toString().split(" ");
	String newValue=" value= "+valueList[0];
	
	context.write(new Text(newKey),new Text(newValue));
}
}
