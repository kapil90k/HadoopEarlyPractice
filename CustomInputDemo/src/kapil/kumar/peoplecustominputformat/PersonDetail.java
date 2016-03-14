package kapil.kumar.peoplecustominputformat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class PersonDetail implements WritableComparable 
{
	private Text id,fName,lName,occupation;
	
	

	public PersonDetail() 
	{
		this.id = new Text();
		this.fName = new Text();
		this.lName = new Text();
		this.occupation = new Text();
	}

	public PersonDetail(Text id, Text fName, Text lName, Text occupation) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.occupation = occupation;
	}

	@Override
	public void readFields(DataInput in) throws IOException 
	{
		id.readFields(in);
		fName.readFields(in);
		lName.readFields(in);
		occupation.readFields(in);
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		id.write(out);
		fName.write(out);
		lName.write(out);
		occupation.write(out);
	}

	@Override
	public int compareTo(Object o) {

		PersonDetail other = (PersonDetail)o;
		int cmp1 = id.compareTo(other.id);
		if(cmp1 != 0){
				return cmp1;
		}
		int cmp2 = fName.compareTo(other.fName);
		if(cmp2 != 0){
				return cmp2;
		}
		int cmp3 = lName.compareTo(other.lName);
		if(cmp3 != 0){
				return cmp3;
		}
		return occupation.compareTo(other.occupation);
	}


	public Text getId() {
		return id;
	}

	public void setId(Text id) {
		this.id = id;
	}

	public Text getfName() {
		return fName;
	}

	public void setfName(Text fName) {
		this.fName = fName;
	}

	public Text getlName() {
		return lName;
	}

	public void setlName(Text lName) {
		this.lName = lName;
	}

	public Text getOccupation() {
		return occupation;
	}

	public void setOccupation(Text occupation) {
		this.occupation = occupation;
	}

	
}
