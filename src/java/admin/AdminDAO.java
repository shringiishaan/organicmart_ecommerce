package admin;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdminDAO
{
	private DataSource dataSource;
			
	public AdminDAO()
	{		
		try 
		{
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("organickmartdb");
		} 
		catch (NamingException e) 
		{
			System.out.println(e);
		}
	}
	
            public boolean isAdmin(String email, String password)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM admin_master where email = '" + email + "'");
			
			if(resultSet.first() && password.equals(resultSet.getString("password"))) 
			{
				return true;
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
		return false;
	}
	
	public boolean isAdmin(String email)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM admin_master where email = '" + email + "'");
			
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
	
	public void updateAdmin(int id,String name, String email, String password)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute("update admin_master set name = '" + name
					+ "',email = '" + email + "', password = '" + password + 
					"' where adminId=" + id + " ;");
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
	
	public void deleteAdmin(int id)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			statement.execute("delete from admin_master where adminId=" + id + " ;");
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
	
	public boolean validateAdmin(String name, String email, String password)
	{
		if(validateAdminName(name) && validateAdminEmail(email) && validateAdminPassword(password))
			return true;
		else return false;
	}
	
	public boolean validateAdminName(String category)
	{
		//TODO
		return true;
	}
	
	public boolean validateAdminEmail(String email)
	{
		//TODO
		return true;
	}
	
	public boolean validateAdminPassword(String password)
	{
		//TODO
		return true;
	}
}
