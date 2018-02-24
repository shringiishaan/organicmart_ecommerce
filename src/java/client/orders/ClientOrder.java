package client.orders;

import client.Client;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import util.Item;

public class ClientOrder 
{
	int orderId;
        Client client;
	Timestamp orderTime;
        String address;
	
	private DataSource dataSource;
			
	
	public ClientOrder(int orderId)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		
		try 
		{
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("organickmartdb");
			connection = dataSource.getConnection();
			statement = connection.createStatement();
		
			resultSet = statement.executeQuery("SELECT * FROM client_order_master where orderId = '" + orderId + "';");
			
			if(resultSet.first()) 
			{
				this.orderId = resultSet.getInt("orderId");
				this.client = new Client(resultSet.getInt("clientId"));
				this.orderTime = resultSet.getTimestamp("orderTime");
				this.address = resultSet.getString("address");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=statement)statement.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=connection)connection.close();} catch (SQLException e) 
			{e.printStackTrace();}
		}
	}
	
	public ClientOrder(int clientId,Timestamp orderTime)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		
		try 
		{
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("organickmartdb");
			connection = dataSource.getConnection();
			statement = connection.createStatement();
		
			resultSet = statement.executeQuery("SELECT * FROM client_order_master where clientId = '" 
                                + clientId + "'"
                                        + "and orderTime='"+orderTime+"';");
			
			if(resultSet.first()) 
			{
				this.orderId = resultSet.getInt("orderId");
				this.client = new Client(resultSet.getInt("clientId"));
				this.orderTime = resultSet.getTimestamp("orderTime");
				this.address = resultSet.getString("address");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=statement)statement.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=connection)connection.close();} catch (SQLException e) 
			{e.printStackTrace();}
		}
	}
	
	public ArrayList<ClientOrderItem> getOrderItems()
	{
		Connection connection=null;
		Statement statement=null;
		ArrayList<ClientOrderItem> list = new ArrayList<ClientOrderItem>();
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select itemId,quantity,costPerUnit,discount from client_order_details where orderId = "
					+ orderId + ";");
			while(resultSet.next())
			{
				list.add(new ClientOrderItem(new Item(resultSet.getInt("itemId")),
                                                                resultSet.getInt("quantity"),
                                                                resultSet.getInt("costPerUnit"),
                                                                resultSet.getInt("discount")));
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		finally 
		{
			try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=statement)statement.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=connection)connection.close();} catch (SQLException e) 
			{e.printStackTrace();}
		}
		
		return list;
	}
	
	public int getOrderId()
	{
		return orderId;
	}
	
	public Client getClient()
	{
		return client;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public Timestamp getOrderTime()
	{
		return orderTime;
	}
}
