package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(name = "ItemImageDisplay", urlPatterns = {"/ItemImageDisplay/*"})
public class ItemImageDisplay extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
            Blob photo = null;
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            
            ServletOutputStream out = response.getOutputStream();
            
            try 
            { 
                Context initContext  = new InitialContext();
                Context envContext  = (Context)initContext.lookup("java:/comp/env");
                DataSource dataSource = (DataSource)envContext.lookup("organickmartdb");
                conn = dataSource.getConnection();

            } catch (Exception e) {
              response.setContentType("text/html");
              out.println("<html><head><title>Person Photo</title></head>");
              out.println("<body><h1>Database Connection Problem.</h1></body></html>");
              return;
            }

            try {
              stmt = conn.createStatement();
              rs = stmt.executeQuery("select "+((Integer.parseInt(request.getParameter("imageId"))==1)?"itemDp":"itemPic")+" from item_master where itemId="+request.getParameter("itemId"));
              if (rs.next()) {
                photo = rs.getBlob(1);
              } else {
                response.setContentType("text/html");
                out.println("<html><head><title>Person Photo</title></head>");
                out.println("<body><h1>No photo found for id="+request.getParameter("itemId")+"</h1></body></html>");
                return;
              }

              response.setContentType("image/gif");
              InputStream in = photo.getBinaryStream();
              int length = (int) photo.length();

              int bufferSize = 1024;
              byte[] buffer = new byte[bufferSize];

              while ((length = in.read(buffer)) != -1) {
                System.out.println("writing " + length + " bytes");
                out.write(buffer, 0, length);
              }

              in.close();
              out.flush();
            } catch (Exception e) {
              response.setContentType("text/html");
              out.println("<html><head><title>Error: Person Photo</title></head>");
              out.println("<body><h1>Error=" + e.getMessage() + "</h1></body></html>");
              return;
            } finally {
              try {
                rs.close();
                stmt.close();
                conn.close();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
    
    /*
    
    
            Statement stmt=null;
            String sql=null;
            BufferedInputStream bin=null;
            BufferedOutputStream bout=null;
            InputStream in =null;
            ServletOutputStream out=null; 
            Connection conn = null;
        try
        {        
                Context initContext  = new InitialContext();
                Context envContext  = (Context)initContext.lookup("java:/comp/env");
                DataSource dataSource = (DataSource)envContext.lookup("organickmartdb");
                conn = dataSource.getConnection();
                  
                response.setContentType("image/jpeg");  
                out = response.getOutputStream();  

                int itemId = Integer.parseInt(request.getParameter("itemId"));
                stmt = conn.createStatement();
                sql = "SELECT itemDp FROM item_master WHERE itemId="+itemId+"";
                ResultSet result = stmt.executeQuery(sql);
                if(result.next()){
                    in=result.getBinaryStream("itemDp");
                }
                bin = new BufferedInputStream(in);  
                bout = new BufferedOutputStream(out);  
                int ch=0;   
                while((ch=bin.read())!=-1)  
                    {  
                    bout.write(ch);  
                }  

        } 
        catch (Exception ex) 
        {
            
        }
        finally
        {
            try
            {
                if(bin!=null)bin.close();  
                if(in!=null)in.close();  
                if(bout!=null)bout.close();  
                if(out!=null)out.close();
                if(conn!=null)conn.close();
            }catch(Exception ex){}
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }*/

}
}