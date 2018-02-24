package client;

import client.orders.ClientOrder;
import client.orders.ClientOrderItem;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.*;

public class Client 
{
	int clientId;
	String  name,email,password,pincode,address,contact;
	private DataSource dataSource;
	
        private Connection getConnection() throws SQLException
        {
            return dataSource.getConnection();
        }
	
	public Client(String email)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		
		try 
		{
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("organickmartdb");
			connection = getConnection();
			statement = connection.createStatement();
		
			resultSet = statement.executeQuery("SELECT * FROM client_master where email = '" + email + "';");
			
			if(resultSet.first()) 
			{
				this.name = resultSet.getString("name");
				this.clientId = resultSet.getInt("clientId");
				this.password = resultSet.getString("password");
				this.email = resultSet.getString("email");
				this.pincode = resultSet.getString("pincode");
				this.address = resultSet.getString("address");
				this.contact = resultSet.getString("contact");
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
	
	public Client(int id)
	{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet = null;
		
		try 
		{
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("organickmartdb");
			connection = getConnection();
			statement = connection.createStatement();
		
			resultSet = statement.executeQuery("SELECT * FROM client_master where clientId = '" + id + "';");
			
			if(resultSet.first()) 
			{
				this.name = resultSet.getString("name");
				this.clientId = resultSet.getInt("clientId");
				this.password = resultSet.getString("password");
				this.email = resultSet.getString("email");
				this.pincode = resultSet.getString("pincode");
				this.address = resultSet.getString("address");
				this.contact = resultSet.getString("contact");
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
	
	public void submitNewOrder(ArrayList<ClientOrderItem> orderItemList,String address)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
                    if(!orderItemList.isEmpty())
                    {
			connection = getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("INSERT into client_order_master "
					+ "(clientId,orderTime,address)"
					+ "values"
					+ "(" + clientId
					+ ",'" + new Timestamp((new java.util.Date()).getTime())
					+ "','" + address
                                        + "');",statement.RETURN_GENERATED_KEYS);
                        ResultSet rs = statement.getGeneratedKeys();
                        int orderId;
                        rs.first();
                        orderId = rs.getInt(1);
                        int counter = orderItemList.size() - 1;
                        while(counter>=0)
                        {
                            statement.execute("INSERT into client_order_details "
                                        + "(orderId,itemId,quantity,costPerUnit,discount)"
                                        + "values"
                                        + "("+orderId+"," 
                                        + orderItemList.get(counter).getItem().getItemID() + "," 
                                        + orderItemList.get(counter).getQuanity() + "," 
                                        + orderItemList.get(counter).getItem().getSellPrice() + "," 
                                        + orderItemList.get(counter).getItem().getDiscount() + ");");
                            counter--;
                        }
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
	
	public ArrayList<ClientOrder> getOrders()
	{
		Connection connection=null;
		Statement statement=null;
		ArrayList<ClientOrder> list = new ArrayList<>();
		ResultSet resultSet = null;
		try 
		{
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select orderId from client_order_master where clientId = "
					+ clientId + " order by orderTime asc;");
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
	
	public void updateName(String name)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = getConnection();
			statement = connection.createStatement();
			
			statement.execute("update client_master set name='" + name + "' where clientId=" + clientId + " ;");
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
	
	public void addToCart(HttpSession session,int itemId,int quantity)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{    
                    if(session.getAttribute("clientItemIdList")==null)
                    {
                        ArrayList<Integer> clientItemIdList=new ArrayList<>(),
                                            clientQuantityList=new ArrayList<>();
                        clientItemIdList.add(itemId);
                        clientQuantityList.add(quantity);
                        session.setAttribute("clientItemIdList",clientItemIdList);
                        session.setAttribute("clientQuantityList",clientQuantityList);
                        session.setAttribute("message","Item added to list !");
                    }
                    else
                    {
                        ArrayList<Integer> clientItemIdList=(ArrayList<Integer>)session.getAttribute("clientItemIdList"),
                                            clientQuantityList=(ArrayList<Integer>)session.getAttribute("clientQuantityList");
                        (clientItemIdList).add(itemId);
                        (clientQuantityList).add(quantity);
                        session.setAttribute("clientItemIdList",clientItemIdList);
                        session.setAttribute("clientQuantityList",clientQuantityList);
                        session.setAttribute("message","Item added to list !");
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
	
	public void removeFromCart(HttpSession session,int itemId)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{    
                    if(session.getAttribute("clientItemIdList")==null)
                    {
                        ArrayList<Integer> clientItemIdList=(ArrayList<Integer>)session.getAttribute("clientItemIdList"),
                                            clientQuantityList=(ArrayList<Integer>)session.getAttribute("clientQuantityList");
                        
                        int index = clientItemIdList.indexOf(itemId);
                        
                        clientItemIdList.remove(index);
                        clientQuantityList.remove(index);
                        
                        session.setAttribute("clientItemIdList",clientItemIdList);
                        session.setAttribute("clientQuantityList",clientQuantityList);
                        session.setAttribute("message","Item removed from Cart !");
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
	
	public void updateEmail(String email)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = getConnection();
			statement = connection.createStatement();
			
			statement.execute("update client_master set email='" + email + "' where clientId=" + clientId + " ;");
                        this.email = email;
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
	
	public void updatePincode(String pincode)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = getConnection();
			statement = connection.createStatement();
			
			statement.execute("update client_master set pincode='" + pincode + "' where clientId=" + clientId + " ;");
                        this.pincode = pincode;
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
	
	public void updateAddress(String address)
	{
		Connection connection=null;
		Statement statement=null;
		try 
		{
			connection = getConnection();
			statement = connection.createStatement();
			
			statement.execute("update client_master set address='" + address + "' where clientId=" + this.clientId + " ;");
                        this.address = address;
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
			connection = getConnection();
			statement = connection.createStatement();
			
			statement.execute("update client_master set password='" + password + "' where clientId=" + clientId + " ;");
                        this.password = password;
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
	
	public int getClientId()
	{
		return clientId;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getPincode()
	{
		return pincode;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getContact()
	{
		return contact;
	}
	
	public String getPassword()
	{
		return password;
	}
}
