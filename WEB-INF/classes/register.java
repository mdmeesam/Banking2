import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class register extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
             throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    HttpSession session = req.getSession(true);
   
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String FirstName= req.getParameter("FirstName");
    String LastName= req.getParameter("LastName");
    String email= req.getParameter("email");
    String tel= req.getParameter("tel");
    
    String temp = "register.html";
    
  
    Connection con = null;
        
    try {
	     Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
		//make a connection
		con=DriverManager.getConnection("jdbc:ucanaccess://C:/apache-tomcat/webapps/bank/WEB-INF/database/h&t.accdb");
		System.out.println("Connection Successful");
		//creating object of statement
		Statement stat=con.createStatement();
        stat.executeUpdate("insert into customer (username,password,fname,lname,email,tel) values ('"+username+"','"+password+"','"+FirstName+"','"+LastName+"','"+email+"',"+tel+")");
	  
      
    	  
    	   
         temp ="login.html";  	
      	
     
      
      res.sendRedirect(temp);  
      
      
    } catch (SQLException e) {
      throw new 
      ServletException("Servlet Could not display records.", e);
    } catch (ClassNotFoundException e) {
      throw new 
      ServletException("JDBC Driver not found.", e);
    } finally {
      try {
        if(con != null) {
          con.close();
          con = null;
        }
      } catch (SQLException e) {}
      out.close();
    }

}
}