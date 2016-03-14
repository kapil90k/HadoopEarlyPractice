package kapil.kumar.peoplecustominputformat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;


public class Locality implements WritableComparable
{

	private Text area;
	private Text city;
	private Text state;

	public Locality()
	{
		area=new Text();
		city=new Text();
		state=new Text();
	}
	
	public Locality(Text area,Text city,Text state)
	{
		this.area=area;
		this.city=city;
		this.state=state;
	}
	
	@Override
	public void readFields(DataInput in) throws IOException 
	{
		area.readFields(in);
		city.readFields(in);
		state.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException 
	{
		area.write(out);
		city.write(out);
		state.write(out);
	}

	/*@Override
	public int compareTo(Object o) 
	{
		Locality other = (Locality)o;
		int cmp = area.compareTo(other.area);
		if(cmp != 0){
				return cmp;
		}
		cmp = city.compareTo(other.city);
		if(cmp != 0){
				return cmp;
		}
		return state.compareTo(other.state);
	}*/

	public Text getArea() {
		return area;
	}

	public void setArea(Text area) {
		this.area = area;
	}

	public Text getCity() {
		return city;
	}

	public void setCity(Text city) {
		this.city = city;
	}

	public Text getState() {
		return state;
	}

	public void setState(Text state) {
		this.state = state;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
