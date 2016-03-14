package kapil.kumar.WordSize;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordSizeReducer extends Reducer<Text,IntWritable,Text,IntWritable> {

}
