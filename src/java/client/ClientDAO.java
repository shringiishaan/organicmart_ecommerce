package client;

import client.orders.ClientOrder;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ClientDAO
{
	private DataSource dataSource;
			
	public ClientDAO()
	{		
		try 
		{
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("organickmartdb");
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean isClient(String email, String password)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM client_master where email = '" + email + "'");
			
			if(resultSet.first() && password.equals(resultSet.getString("password"))) 
			{
				return true;
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
		return false;
	}
	
	public boolean isClient(String email)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM client_master where email = '" + email + "'");
			
			if(resultSet.first()) 
			{
				return true;
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
		return false;
	}
	
	public void createNewClient(String name, String email, String password, String pincode, String address, String contact)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute("INSERT into client_master "
					+ "(name, email,password,pincode,address,contact)"
					+ "values"
					+ "('" + name
					+ "','" + email 
					+ "','" + password 
					+ "','" + pincode 
					+ "','" + address 
                                        + "','" + contact + "');");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		finally 
		{
			try { if(null!=statement)statement.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=connection)connection.close();} catch (SQLException e) 
			{e.printStackTrace();}
		}
	}
	
	public ArrayList<ClientOrder> getOrders()
	{
		Connection connection=null;
		Statement statement=null;
		ArrayList<ClientOrder> list = new ArrayList<>();
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select orderId from client_order_master order by orderTime desc;");
			while(resultSet.next())
			{
				list.add(new ClientOrder(resultSet.getInt("orderId")));
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
	
	public void updateClient(int id,String name, String email, String password)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute("update client_master set name = '" + name
					+ "',email = '" + email + "', password = '" + password + 
					"' where clientId=" + id + " ;");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		finally 
		{
			try { if(null!=statement)statement.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=connection)connection.close();} catch (SQLException e) 
			{e.printStackTrace();}
		}
	}
	
	public void deleteClient(int id)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			statement.execute("delete from client_master where clientId=" + id + " ;");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		finally 
		{
			try { if(null!=statement)statement.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=connection)connection.close();} catch (SQLException e) 
			{e.printStackTrace();}
		}
	}
	
	public ArrayList<Client> getClients(String orderBy)
	{
		Connection connection=null;
		Statement statement=null;
		ArrayList<Client> list = new ArrayList<Client>();
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select clientId from client_master order by "
					+ orderBy + " ;");
			while(resultSet.next())
			{
				list.add(new Client(resultSet.getInt("clientId")));
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
	
	public boolean validateClient(String name, String email, String password)
	{
		if(validateClientName(name) && validateClientEmail(email) && validateClientPassword(password))
			return true;
		else return false;
	}
	
	public boolean validateClientName(String category)
	{
		//TODO
		return true;
	}
	
	public boolean validateClientEmail(String email)
	{
		//TODO
		return true;
	}
	
	public boolean validateClientPassword(String password)
	{
		//TODO
		return true;
	}
}
