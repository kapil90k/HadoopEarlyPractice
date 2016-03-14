package kapil.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.datastax.driver.core.querybuilder.QueryBuilder;

public class GettingStarted {

	public static void main(String[] args) 
	{
//		()@
		Cluster cluster;
		Session session;
		String line="This is rahul kumar";
		cluster=Cluster.builder().addContactPoint("127.0.0.1").withRetryPolicy(DefaultRetryPolicy.INSTANCE).withLoadBalancingPolicy(
                new TokenAwarePolicy(new DCAwareRoundRobinPolicy())).build();
		session=cluster.connect("mykeyspace2");
		System.out.println(cluster.getClusterName());
		
		PreparedStatement pstmt=session.prepare("insert into mrdemo(key) values(?)");
		session.execute(pstmt.bind(line));
		
		
		
		
		/*session.execute("insert into users(firstname,lastname,email,age,city) values('kapil','kumar','kapil.m@g.com',25,'Agra')");
		session.execute("insert into users(lastname,firstname,email,age,city) values('saxena','mohit','mgupta.m@g.com',26,'Mathura')");
		
		PreparedStatement statement=session.prepare("insert into users(firstname,lastname,email,age,city) values(?,?,?,?,?)");
		BoundStatement boundStmt=new BoundStatement(statement);
		session.execute(boundStmt.bind("shubh","kumar","asdj",12,"mathura"));
		session.execute(boundStmt.bind("rajkumar","hirani","raju@hirani.com",32,"Faizabad"));
		ResultSet results=session.execute("select * from users");
		
		for (Row row : results) 
		{
			String email=row.getString(0);
			int age=row.getInt(1);
			String city=row.getString(2);
			String fname=row.getString(3);
			String lname=row.getString(4);
			System.out.format("%s %d %s %s %s\n", email,age,city,fname,lname);
		}*/
		
		/*Statement stmt=QueryBuilder.select().all().from("mykeyspace2", "users");
		ResultSet rs=session.execute(stmt);
		for (Row row : rs) {
			String email=row.getString(0);
			int age=row.getInt(1);
			String city=row.getString(2);
			String fname=row.getString(3);
			String lname=row.getString(4);
			System.out.format("%s %d %s %s %s\n", email,age,city,fname,lname);
		}*/
		
		/*
		ResultSet resultEmp=session.execute("select * from employee2");
		for (Row rowEmp : resultEmp) 
		{
//			rowEmp.get
		}*/
		
		
//		session.execute("create table demo(id int primary key,name text)");
		
		
//		for (Row row : results)
//		{
//			row.getString("id");
//			row.getString("comp_addresses");
//		}
		
		cluster.close();
		
		
		
	}

}
