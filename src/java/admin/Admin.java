package admin;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class Admin 
{
	int adminId;
	String  name,email,password;
	
	private DataSource dataSource;
			
	
	public Admin(String email)
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
		
			resultSet = statement.executeQuery("SELECT * FROM admin_master where email = '" + email + "';");
			
			if(resultSet.first()) 
			{
				this.name = resultSet.getString("name");
				this.adminId = resultSet.getInt("adminId");
				this.password = resultSet.getString("password");
				this.email = email;
			}
		} 
		catch (SQLException | NamingException e) 
		{
                    
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
	
	public Admin(int id)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		
		try 
		{
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("shivharidb");
			connection = dataSource.getConnection();
			statement = connection.createStatement();
		
			resultSet = statement.executeQuery("SELECT * FROM admin_master where adminId = '" + id + "';");
			
			if(resultSet.first()) 
			{
				this.adminId = id;
				this.name = resultSet.getString("name");
				this.email = resultSet.getString("email");
				this.password = resultSet.getString("password");
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
	
	public void updateName(String name)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			statement.execute("update admin_master set name='" + name + "' where adminId=" + adminId + " ;");
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
	
	public void updateEmail(String email)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			statement.execute("update admin_master set email='" + email + "' where adminId=" + adminId + " ;");
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
	
	public void updatePassword(String password)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			statement.execute("update admin_master set password='" + password + "' where adminId=" + adminId + " ;");
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
	
	public String getName()
	{
		return name;
	}
	
	public int getAdminId()
	{
		return adminId;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getPassword()
	{
		return password;
	}
}
