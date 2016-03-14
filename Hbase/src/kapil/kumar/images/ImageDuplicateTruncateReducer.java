package kapil.kumar.images;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ImageDuplicateTruncateReducer extends Reducer<Text,Text,Text,Text> 
{
	public void reduce(Text key, Iterable<Text> values, Context context) throws InterruptedException,IOException
	{
		String imageFilePath=null;
		for(Text filePath:values)
		{
			imageFilePath=filePath.toString();
			break;
		}
		context.write(new Text(imageFilePath), key);
	}
}
