package kapil.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class CassandraHelper {

	public void addKey2(String key)
	{
		Cluster cluster=Cluster.builder().addContactPoint("127.0.0.1").build();
		Session session=cluster.connect("mykeyspace2");
		PreparedStatement pstmt=session.prepare("insert into mrdemo(key) values(?)");
		session.execute(pstmt.bind(key));
		cluster.close();
	}
}/*
	private Cluster cluster;
	private Session session;
	private PreparedStatement prepareStatement;
	private String query="INSERT INTO mykeyspace2.mrdemo(key) VALUES(?)";
	
	public void createConnection(String host)
	{
		this.cluster=Cluster.builder().addContactPoint(host).build();
		System.out.println("connected to cluster: "+cluster.getClusterName());
		this.session=cluster.connect("mykeyspace2");
		this.prepareQuery();
	}

	private void prepareQuery() 
	{
		this.prepareStatement=this.session.prepare(this.query);
	}

	public Session getSession() 
	{
		if (this.session==null && (this.cluster==null || this.cluster.isClosed())) 
		{
			System.out.println("cluster is not started or is down");
		}
		else if (session.isClosed())
		{
			System.out.println("session has been closed, creating again");
			this.session=this.cluster.connect("mykeyspace2");
		}
		return this.session;
	}

	public void addKey(String key) 
	{
		Session session=this.getSession();
		if (key.length()>0) 
		{
			session.execute(this.prepareStatement.bind(key));
		}
		
	}

	public void closeConnection() 
	{
		this.cluster.close();
	}
	
	

}*/
