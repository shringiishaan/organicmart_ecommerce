package util;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;

public class ItemDAO
{
	private DataSource dataSource;
			
	public ItemDAO()
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
	
	public boolean isItem(int id)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM item_master where itemId = '" + id + "'");
			
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
	
	public boolean isItem(String name)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM item_master where name = '" + name + "'");
			
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
	
	public void createNewItem(String name, 
                                    String unit, 
                                    String description,
                                    InputStream inputStream1,
                                    InputStream inputStream2)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
                    
			connection = dataSource.getConnection();
			
                        PreparedStatement pstmtSave = connection.prepareStatement("INSERT into item_master "
                                                        + "(name,unit,description,itemDP,itemPic,sellPrice,qtyLimit,discount)"
                                                        + "values"
                                                        + "(?,?,?,?,?,?,?,?);");
                        if (inputStream1 != null && inputStream2 != null) 
                        {
                            pstmtSave.setString(1,name);
                            pstmtSave.setString(2,unit);
                            pstmtSave.setString(3,description);
                            pstmtSave.setBlob(4, inputStream1);
                            pstmtSave.setBlob(5, inputStream2);
                            pstmtSave.setInt(6,0);
                            pstmtSave.setInt(7,0);
                            pstmtSave.setInt(8,0);
                            pstmtSave.executeUpdate();
                        }

                        
                
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
	
	public void createNewCategory(String name)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
                        statement = connection.createStatement();			
			statement.execute("INSERT into item_category_master "
                                                        + "(name)"
                                                        + "values"
                                                        + "('"+name+"');");                
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
	
	public void deleteCategory(String name)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
                        statement = connection.createStatement();			
			statement.execute("delete from item_category_master "
                                                        + "where name = "
                                                        + "'"+name+"';");                
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
	
	public int getCategoryId(String name)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
                int id=0;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select categoryId from item_category_master where name=\""
                                +name+"\";");
			if(resultSet.next())
			{
				id = resultSet.getInt("categoryId");
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
		
		return id;
	}
	
	public void deleteItem(int id)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			statement.execute("delete from item_master where itemId=" + id + " ;");
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
	
	public ArrayList<String> getCategories()
	{
		Connection connection=null;
		Statement statement=null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select name from item_category_master;");
			while(resultSet.next())
			{
				list.add(resultSet.getString("name"));
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
	
	public ArrayList<Item> getItems(String where,String orderBy)
	{
		Connection connection=null;
		Statement statement=null;
		ArrayList<Item> list = new ArrayList<Item>();
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select itemId from item_master where "+where+" order by "
					+ orderBy + " ;");
			while(resultSet.next())
			{
				list.add(new Item(resultSet.getInt("itemId")));
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
	
	public ArrayList<Item> getItemsByCategory(String category,String orderBy)
	{
		Connection connection=null;
		Statement statement=null;
		ArrayList<Item> list = new ArrayList<Item>();
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(
                                "select itemId,name,discount,sellPrice " +
                                "from item_master " +
                                "where itemId in " +
                                "	(SELECT itemId " +
                                "	 FROM item_category_master" +
                                "	 natural join item_categories" +
                                "	 where name ='"+category+"')"
                             + " order by "+orderBy+";");
			while(resultSet.next())
			{
				list.add(new Item(resultSet.getInt("itemId")));
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
	
	public boolean validateItem(String name, String email, String password)
	{
		if(validateItemName(name) && validateItemEmail(email) && validateItemPassword(password))
			return true;
		else return false;
	}
	
	public boolean validateItemName(String category)
	{
		//TODO
		return true;
	}
	
	public boolean validateItemEmail(String email)
	{
		//TODO
		return true;
	}
	
	public boolean validateItemPassword(String password)
	{
		//TODO
		return true;
	}
	
	public boolean validateItemCity(String city)
	{
		//TODO
		return true;
	}
	
	public boolean validateItemContact(String contact)
	{
		//TODO
		return true;
	}
}
