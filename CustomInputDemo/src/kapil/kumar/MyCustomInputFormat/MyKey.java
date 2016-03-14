package kapil.kumar.MyCustomInputFormat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class MyKey implements WritableComparable
{

	private Text sensorType,timeStamp,status;
	
	public MyKey() {
		super();
		this.sensorType = new Text();
		this.timeStamp = new Text();
		this.status = new Text();
	}
	
	
	
	public MyKey(Text sensorType, Text timeStamp, Text status) {
		super();
		this.sensorType = sensorType;
		this.timeStamp = timeStamp;
		this.status = status;
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

}
