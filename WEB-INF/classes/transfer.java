import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class transfer extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
             throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    HttpSession session = req.getSession(true);

    //String uname = req.getParameter("username");
    //String pass = req.getParameter("password");
    String uname = (String) session.getAttribute("userName");
    String pass = (String) session.getAttribute("passWord");
    String acc = (String) session.getAttribute("accNum");
    int account=Integer.parseInt(acc);
    
    String payee=req.getParameter("payname");
    String sendacc=req.getParameter("accnum");
    int sendamt=Integer.parseInt(req.getParameter("amount"));
    String desc=req.getParameter("desc");
    
    String temp = "transfer.html";
   
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
	//System.out.println(dtf.format(localDate)); //2016/11/1
	String ddate=dtf.format(localDate);
	int bal=2;
	//need to read from db current bal for sendeer
	//then use that - with send amt
	//insert into db with updated bal
	
    Connection con = null;
        
    try {
	     Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
		//make a connection
		con=DriverManager.getConnection("jdbc:ucanaccess://C:/apache-tomcat/webapps/bank/WEB-INF/database/h&t.accdb");
		System.out.println("Connection Successful");
		//creating object of statement
		
		Statement stat=con.createStatement();
        stat.executeUpdate("insert into transaction (account,description,balance,ddate,amount) values ('"+acc+"','"+desc+"',"+bal+",'"+ddate+"',"+sendamt+")");
	  
		
		
		
        //Statement stat=con.createStatement();
        //ResultSet rs = stat.executeQuery("SELECT * FROM transaction where account='" + account + "'");
       // System.out.println("commencing search");
      // displaying records
     // while(rs.next()) {   	  
    	  
    	 /*out.println("<tr>");
    	 
    	 out.println("<td>"+account+"</td>");
    	 out.println("<td>"+rs.getObject(2).toString()+"</td>");
    	 out.println("<td>"+rs.getObject(6).toString()+"</td>");
    	 out.println("</tr>");
    	 */
    	 
    	// System.out.println("Unable while loop");
    	 /*out.print("<tr><td>");
	      	out.print(rs.getObject(3).toString());
	        out.print("\t");
	        out.print(rs.getObject(4).toString());
	        out.print("\t\t");
	        out.print(rs.getObject(5).toString());
	        out.print("\t\t&nbsp;&nbsp;");
	        out.print(rs.getObject(7).toString());
	        out.print("\t\t&nbsp;&nbsp;");
	        out.print(rs.getObject(8).toString());
	        out.print("\n");
	        out.print("</td></tr>");*/
	        
         temp ="transactions";  	
      	
    //  }
      System.out.println("able exit loop");
      
      
      res.sendRedirect(temp);  
      
      
      System.out.println("end of code");
      
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