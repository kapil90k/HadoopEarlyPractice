package kapil.kumar.custominput;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<MyKey, MyValue, Text, Text> {
        
          protected void map(MyKey key, MyValue value, Context context)
              throws java.io.IOException, InterruptedException {
        	 
        	 // context.write(key.getTimestamp(), value.getValue2());   working
        	  
            String sensor = key.getSensorType().toString();
            
            if(sensor.toLowerCase().equals("a")){
            	context.write(value.getValue1(),value.getValue2());
            }
            		
          }  
}