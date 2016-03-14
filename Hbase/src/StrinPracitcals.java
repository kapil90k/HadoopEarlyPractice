import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class StrinPracitcals {

	/**
	 * @param args
	 * @throws IOException 
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

		String line="january 14 16 15 10 13 16 12 13 65 45 12 31 10 10 12 45 23 23 13 12 75 65 12 26 17 19 20 21 23 25";
		StringTokenizer tokens=new StringTokenizer(line," ");
		//System.out.println(tokens.nextElement());
		//System.out.println(tokens.nextElement());
		tokens.nextToken();
		int max=0;
		int min=200;
		while (tokens.hasMoreTokens())
		{
			int temp=Integer.parseInt(tokens.nextToken());
			if (temp>max) {
				max=temp;
			}
			if(temp<min) {
				min=temp;
			}
		}
		String newVal="(max="+max+",min="+min+")";
		//System.out.println("max temp= "+max+"min temp"+min);
		System.out.println(newVal);
	}*/
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String line="january 14 16 15 10 13 16 12 13 65 45 12 31 10 10 12 45 23 23 13 12 75 65 12 26 17 19 20 21 23 25";
		
		int max=0;
		int min=200;
		String strs[]=line.split(" ");
		
		for(int i=1;i<strs.length;i++)
		{
			int temp=Integer.parseInt(strs[i]);
			if (temp>max) {
				max=temp;
			}
			if(temp<min) {
				min=temp;
			}
		}
		
		System.out.println("max temp= "+max+"\nmin temp"+min);
	}*/
	
	
	/*public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("/home/edureka/Desktop/datasets/aadhar.txt"));
		String sCurrentLine;
		while((sCurrentLine=br.readLine())!=null)
		{
			StringTokenizer tokens=new StringTokenizer(sCurrentLine, "\t");
			while(tokens.hasMoreTokens())
			{
				System.out.print(tokens.nextToken()+" ~");
			}
			System.out.println();
		}
		
	}*/
	
	/*public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("/home/edureka/Desktop/datasets/aadhar.txt"));
		String line;
		while((line=br.readLine())!=null)
		{
			String[] tokens=line.split("\t");
			for(int i=0;i<tokens.length;i++)
			{
				System.out.print(tokens[i]+"~");
			}
			System.out.println();
		}
		
	}*/
	
	
	/*public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("/home/edureka/Desktop/datasets/crime2.csv"));
		String line;
		while((line=br.readLine())!=null)
		{
			String[] tokens=line.split(",");
			for(int i=0;i<tokens.length;i++){
			System.out.print(tokens[i]+"~");}
			System.out.println();
			
			if(tokens[1].trim().equalsIgnoreCase("\"rape\""))
			{
				System.out.println(tokens[1]+"~"+Integer.parseInt(tokens[2]));
			}
			//System.out.println(line);
		}
		
	}*/
	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("/home/edureka/Desktop/datasets/HotColdCache.txt"));
		String line;
		while((line=br.readLine())!=null)
		{
			String[] tokens=line.split("\t");
			
			System.out.println(tokens[0]+" ~ "+tokens[1]);
			
		}
		
	}
	
	
	
}
