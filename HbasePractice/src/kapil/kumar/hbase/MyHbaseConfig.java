package kapil.kumar.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class MyHbaseConfig {

	Configuration conf=new HBaseConfiguration();
	public void createTable(String tname,String[] cFamily) throws MasterNotRunningException, ZooKeeperConnectionException, IOException
	{
		HBaseAdmin admin=new HBaseAdmin(conf);
		HTableDescriptor hDesc = null;
		if (admin.tableExists(tname))
		{
			System.out.println("Table already exists");
		}
		else
		{
			hDesc=new HTableDescriptor(tname);
			for (int i = 0; i < cFamily.length; i++)
			{
				hDesc.addFamily(new HColumnDescriptor(cFamily[i]));
			}
		}
		admin.createTable(hDesc);
	}
	
	public void addFamily(String tableName,String newFamily) throws MasterNotRunningException, ZooKeeperConnectionException, IOException
	{
		HBaseAdmin admin=new HBaseAdmin(conf);
		if (!admin.tableExists(tableName)) 
		{
			System.out.println("Table doesn't exists");
			return;
		}
		HTableDescriptor hDesc=new HTableDescriptor(tableName);
		hDesc.addFamily(new HColumnDescriptor(newFamily));
	}
	
	public void addRecord(String tableName,String columnFamily,String columnName,String rowKey,String value) throws IOException
	{
		HTable table=new HTable(conf, tableName);
		Put put=new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(columnFamily),Bytes.toBytes(columnName),Bytes.toBytes(value));
		table.put(put);
	}
	
	public void deleteRecord(String tableName,String rowKey) throws IOException
	{
		HTable table=new HTable(conf, tableName);
		Delete del=new Delete(rowKey.getBytes());
		table.delete(del);
	}

	
	public void deleteTable(String tableName) throws IOException 
	{
		HBaseAdmin admin=new HBaseAdmin(conf);
		admin.disableTable(tableName);
		admin.deleteTable(tableName);
	}
	
	public void getOneRecord(String tableName,String rowKey)
	{
		
	}
	
	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException 
	{
		String tname="student";
		String[] cFamily={"home","college","subjects","friends"};
		MyHbaseConfig hconf=new MyHbaseConfig();
//		hconf.createTable(tname,cFamily);
//		hconf.addFamily("studehknt","girlFriends");
		
		//hconf.addRecord("student","home","house_no","stu1","802");
//		hconf.deleteRecord("student","802");
		hconf.deleteTable("Bangalore");
		hconf.getOneRecord("employee","emp1");
	}



}
