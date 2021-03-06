package kapil.kumar.images;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ImageDuplicateTruncateMapper extends Mapper<Text,BytesWritable,Text,Text>
{
	public void map(Text key, BytesWritable value, Context context) throws InterruptedException,IOException
	{
		byte[] arrayOfImage = value.getBytes();
		String md5OfImage=calculateMD5(arrayOfImage);
		context.write(new Text(md5OfImage), key);
	}

	private String calculateMD5(byte[] arrayOfImage) {
		String hexString=new String();
		try
		{
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(arrayOfImage);
			byte[] convertedFile1= md.digest();
			
			for(int i=0;i<convertedFile1.length;i++)
			{
				hexString+=Integer.toString((convertedFile1[i] & 0xff) + 0x100,16).substring(1);
			}
		}
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		return hexString;
	}
}
