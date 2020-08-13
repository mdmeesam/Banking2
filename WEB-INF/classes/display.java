import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class display extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    HttpSession session = req.getSession(true);
    
    

    out.print("<html><head>");
    out.print("</head><body>");     
	out.print("<table><tr><td>");
	out.println("Welcome " + (String) session.getAttribute("userName") + "!");
	out.println("</td></tr>");
	out.println("<tr><td>");
    out.print("<font color=green>First Name\t\t Last Name ");
    out.println("\t\t Mobile\n\n</font>");
	out.print("</td></tr>");
	 
    Connection con = null;
    
    try {
	  	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
		//make a connection
		con=DriverManager.getConnection("jdbc:ucanaccess://C:/apache-tomcat/webapps/bank/WEB-INF/database/h&t.accdb");
		System.out.println("Connection Successful");
		//creating object of statement
		Statement stat=con.createStatement();
		ResultSet rs = stat.executeQuery("select * from customer");
        
        	//displaying records
	      while(rs.next()) {
	      	out.print("<tr><td>");
	      	out.print(rs.getObject(3).toString());
	        out.print("\t");
	        out.print(rs.getObject(4).toString());
	        out.print("\t\t");
	        out.print(rs.getObject(5).toString());
	        out.print("\n");
	        out.print("</td></tr>");
	      }
	      out.print("</table>");
	      
	out.println("</body>");
	out.println("</html>");

      	
    } catch (SQLException e) {
      throw new 
      ServletException("Servlet Could not display records.", e);
    } catch (ClassNotFoundException e) {
      throw new 
      ServletException("JDBC Driver not found.", e);
    } 
    finally {
      try {
        if(con != null) {
          con.close();
          con = null;
        }
      } catch (SQLException e) {}
    }

    out.close();
  }
}