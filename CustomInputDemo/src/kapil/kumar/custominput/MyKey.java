package kapil.kumar.custominput;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;


public class MyKey implements WritableComparable{
	private Text SensorType,timestamp,status;
	
	public MyKey(){
		this.SensorType = new Text();
		this.timestamp = new Text();
		this.status = new Text();
	}
	public MyKey(Text SensorType,Text timestamp,Text status){
		this.SensorType = SensorType;
		this.timestamp = timestamp;
		this.status = status;		
	}
	public void readFields(DataInput in) throws IOException{		//who is calling these unimplemented methods?
		SensorType.readFields(in);						//What is happening here?
		timestamp.readFields(in);						//what is inside object in?
		status.readFields(in);
	}
	
	public void write(DataOutput out) throws IOException{
		SensorType.write(out);							//Same doubt as above
		timestamp.write(out);
		status.write(out);
	}
	public int compareTo(Object o){						//what is inside Object object?
		MyKey other = (MyKey)o;
		int cmp = SensorType.compareTo(other.SensorType);
		if(cmp != 0){
				return cmp;
		}
		cmp = timestamp.compareTo(other.timestamp);
		if(cmp != 0){
				return cmp;
		}
		return status.compareTo(other.status);
		
	}
	public Text getSensorType() {
		return SensorType;									//When the get method is getting call?
	}
	public void setSensorType(Text sensorType) {			//when the set method is getting call?
		SensorType = sensorType;
	}
	public Text getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Text timestamp) {
		this.timestamp = timestamp;
	}
	public Text getStatus() {
		return status;
	}
	public void setStatus(Text status) {
		this.status = status;
	}
	

}
