package kapil.kumar.temperature;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

public class MrUNitCode2 extends TestCase {

	public static class maptest extends								//This is my map function
			Mapper<LongWritable, Text, Text, IntWritable> {
		Text day = new Text();

		public void map(LongWritable key, Text value, Context ct)
				throws IOException, InterruptedException {
			String[] line = value.toString().split(",");
			int val = Integer.parseInt(line[0]);
			day.set(line[1]);
			ct.write(day, new IntWritable(val));

		}
	}

	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;		//created a variable of MapDriver

	public void setUp() {					
		new maptest();
		mapDriver = MapDriver.newMapDriver(new maptest());			//initializing object of MapDriver
	}

	@Test
	/*public void testMapper() {										//actual logic to run the test on map function
		try {
			mapDriver
					.withInput(new LongWritable(),
							new Text("1,sunday,abhay,holiday"))
					.withOutput(new Text("sunday"), new IntWritable(1))
					.runTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	
	public void testMapper()
	{
		try {
			mapDriver.withInput(new LongWritable(), new Text("1,sunday,abhay,holiday")).withOutput(new Text("sunday"), new IntWritable(1)).runTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
