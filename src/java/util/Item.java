package util;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.*;

public class Item 
{
	int itemId,sellPrice, qtyLimit,discount;
	String  name,unit,description;
	
	private DataSource dataSource;
			
	
	public Item(String name)
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
		
			resultSet = statement.executeQuery("SELECT * FROM item_master where name = '" + name + "';");
			
			if(resultSet.first()) 
			{
				this.itemId = resultSet.getInt("itemId");
				this.sellPrice = resultSet.getInt("sellPrice");
				this.qtyLimit = resultSet.getInt("qtyLimit");
				this.discount = resultSet.getInt("discount");
				this.name = resultSet.getString("name");
				this.unit = resultSet.getString("unit");
				this.description = resultSet.getString("description");
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
	
	public Item(int id)
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
		
			resultSet = statement.executeQuery("SELECT * FROM item_master where itemId =" + id + ";");
			
			if(resultSet.first()) 
			{
				this.itemId = resultSet.getInt("itemId");
				this.sellPrice = resultSet.getInt("sellPrice");
				this.qtyLimit = resultSet.getInt("qtyLimit");
				this.discount = resultSet.getInt("discount");
				this.name = resultSet.getString("name");
				this.unit = resultSet.getString("unit");
				this.description = resultSet.getString("description");
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
	
	public boolean hasStock(int quantityValue)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
		
			resultSet = statement.executeQuery("SELECT * FROM item_master where itemId=" + this.itemId + ";");
			
			if(resultSet.first()) 
			{
				return (quantityValue<resultSet.getInt("quantityValue"));
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
	
	public void setItemDp(InputStream inputStream)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
                    connection = dataSource.getConnection();

                    PreparedStatement pstmtSave = connection.prepareStatement(" update item_master "
                                                    + " set itemDp=? "
                                                    + " where itemId="+this.itemId+";");
                    if (inputStream != null) 
                    {
                        pstmtSave.setBlob(1, inputStream);
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
	
	public void removeDP()
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
                    connection = dataSource.getConnection();
                    connection.prepareStatement(" update item_master "
                                                    + " set itemDp=null "
                                                    + " where itemId="+this.itemId+";").executeUpdate();
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
	
	public void setItemPic(InputStream inputStream)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
                    connection = dataSource.getConnection();

                    PreparedStatement pstmtSave = connection.prepareStatement(" update item_master "
                                                    + " set itemPic=? "
                                                    + " where itemId="+this.itemId+";");
                    if (inputStream != null) 
                    {
                        pstmtSave.setBlob(1, inputStream);
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
	
	public void setOrderQuantityLimit(int quantityValue)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute("update item_master set qtyLimit = " 
                                + quantityValue
					+ " where itemId=" + this.itemId + " ;");
                        this.qtyLimit = quantityValue;
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
	
	public void setSellPrice(int sellPrice)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute("update item_master set sellPrice = '" 
                                + sellPrice + "' where itemId=" + this.itemId + " ;");
                        this.sellPrice = sellPrice;
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
	
	public void setDiscount(int discount)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute("update item_master set discount = '" 
                                            + discount + "' where itemId=" + this.itemId + " ;");
                        this.discount = discount;
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
	
	public void setName(String name)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute("update item_master set name = '" 
                                            + name + "' where itemId=" + this.itemId + " ;");
                        this.name = name;
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
	
	public void setUnit(String unit)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute("update item_master set unit = '" 
                                            + unit + "' where itemId=" + this.itemId + " ;");
                        this.unit = unit;
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
	
	public void setDescription(String description)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute("update item_master set description = '" 
                                            + description + "' where itemId=" + this.itemId + " ;");
                        this.description = description;
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
	
	public int getOrderedQty()
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select sum(quantity) from client_order_details where itemId="
					+ this.itemId + " ;");
			if(resultSet.first())
			{
				return resultSet.getInt("sum(quantity)");
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
		
		return 0;
	}
	
	public void addCategory(int categoryId)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
                        statement = connection.createStatement();			
			statement.execute("INSERT into item_categories "
                                                        + "(itemId,categoryId)"
                                                        + "values"
                                                        + "("+itemId+","+categoryId+");");                
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
			resultSet = statement.executeQuery("select item_category_master.name "
                                + "from item_category_master join item_categories"
                                + " where item_category_master.categoryId = item_categories.categoryId "
                                + " and item_categories.itemId = "+itemId
                                + " order by item_category_master.name asc;");
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
	
	public void removeCategory(int categoryId)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
                        statement = connection.createStatement();			
			statement.execute("delete from item_categories "
                                                        + "where categoryId = "
                                                        + categoryId+" and itemId = "+itemId+";");                
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
	
	public void addPincode(String pincode)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
                        statement = connection.createStatement();			
			statement.execute("INSERT into item_availability "
                                                        + "(itemId,pincode)"
                                                        + "values"
                                                        + "("+itemId+",'"+pincode+"');");                
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
	
	public void removePincode(String pincode)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = dataSource.getConnection();
                        statement = connection.createStatement();			
			statement.execute("delete from item_availability "
                                                        + "where pincode = '"
                                                        + pincode+"' and itemId = "+itemId+";");                
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
	
	public ArrayList<String> getPincodeList()
	{
		Connection connection=null;
		Statement statement=null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select pincode from item_availability where itemId="+itemId+";");
			while(resultSet.next())
			{
				list.add(resultSet.getString("pincode"));
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
	
	public Boolean isAvailableAt(String pincode)
	{
		return (getPincodeList().contains(pincode));
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getItemID()
	{
		return itemId;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getSellPrice()
	{
		return sellPrice;
        }
	
	public int getQtyLimit()
	{
		return qtyLimit;
        }
	
	public int getDiscount()
	{
		return discount;
        }
	
	public String getUnit()
	{
		return unit;
	}
}
