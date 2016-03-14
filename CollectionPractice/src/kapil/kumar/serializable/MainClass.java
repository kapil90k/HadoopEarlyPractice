package kapil.kumar.serializable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainClass {

	public static void main(String[] args) {
		Employee emp = new Employee();
		emp.setAge(20);
		emp.setCourse("mca");
		emp.setName("Kapil Kumar");
		System.out.println(emp.getAge());
		System.out.println(emp.getCourse());
		System.out.println(emp.getName());
		

		FileOutputStream fis=null;
		ObjectOutputStream ois=null;
		try 
		{
			fis = new FileOutputStream("/home/edureka/Desktop/ewmp.ser");
			ois = new ObjectOutputStream(fis);
			ois.writeObject(emp);
		}
		catch (FileNotFoundException fnfe) 
		{
			System.out.println("didn't find specified file");
		}
		catch (IOException ioe) 
		{
			System.out.println("Some input output exception occured");
		}
		finally 
		{
			try 
			{
				fis.close();
				ois.close();
			}
			catch (IOException e) 
			{
				System.out.println("====Some input output exception occured");
			}
		}
	}

}
