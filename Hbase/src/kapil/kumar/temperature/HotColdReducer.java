package kapil.kumar.temperature;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HotColdReducer extends Reducer<Text, IntWritable, Text, IntWritable> 
{

}
