import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class login extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
             throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    HttpSession session = req.getSession(true);

    String uname = req.getParameter("username");
    String pass = req.getParameter("password");
    
    String temp = "login.html";
   
    
    out.println("<!DOCTYPE HTML>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Overview</title>");
    out.println("<meta charset='utf-8' />");
    out.println("<meta name='viewport' content='width=device-width, initial-scale=1, user-scalable=no' />");
    out.println("<meta name='description' content='' />");
    out.println("<meta name='keywords' content='' />");
    out.println("<link rel='stylesheet' href='assets/css/main.css' />");
    out.println("</head>");
    out.println("<body class='is-preload'>");
    out.println("<header id='header'>");
    out.println("<a class='logo' href='index.html'>Decenturion</a>");
    out.println("<nav>");
    out.println("<a href='#menu'>Menu</a>");
    out.println("</nav>");
    out.println("</header>");
    out.println("<nav id='menu'>");
    out.println("<ul class='links'>");
    out.println("<li><a href='login?username="+uname+"&password="+pass+"'>Account Overview</a></li>");
    //?username=test&password=test
    //out.println("Welcome " + (String) session.getAttribute("userName") + "!");
    out.println("<li><a href='transactions'>Transactions</a></li>");
    out.println("<li><a href='transfer.html'>Transfer Funds</a></li>");
    out.println("<li><a href='prod.html'>Products</a></li>");
    out.println("<li><a href='contact.html'>Contact Us</a></li>");
    out.println("<li><a href='login.html'>Logout</a></li>");
    out.println("</ul>");
    out.println("</nav>");
    out.println("<div id='heading' >");
    out.println("<h1>My Account</h1>");
    out.println("</div>");
    out.println("<section id='main' class='wrapper'>");
    out.println("<div class='inner'>");
    out.println("<div class='content'>");
    out.println("<header>");
    out.println("<h2>Overview</h2>");
    out.println("</header>");
    out.println("<div class='table-wrapper'>");
    out.println("<table class='alt'>");
    out.println("<thead>");
    out.println("<tr>");
    out.println("<th>Name</th>");
    out.println("<th>Account Number</th>");
    out.println("<th>Balance</th>");
    out.println("</tr>");
    out.println("</thead>");

	
	
    Connection con = null;
        
    try {
	     Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
		//make a connection
		con=DriverManager.getConnection("jdbc:ucanaccess://C:/apache-tomcat/webapps/bank/WEB-INF/database/h&t.accdb");
		System.out.println("Connection Successful");
		//creating object of statement
		Statement stat=con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM customer where username='" + uname + "' and password='" + pass + "'");
	  
      // displaying records
      while(rs.next()) {   	  
    	  
    	 session.setAttribute("userName", uname);//store username in session
    	 session.setAttribute("passWord", pass);
    	 session.setAttribute("accNum", rs.getObject(7).toString());
    	 
    	 out.println("<tbody>");
    	 out.println("<tr>");
    	 out.println("<td>"+rs.getObject(3).toString()+"  "+rs.getObject(4).toString()+"</td>");
    	 out.println("<td>"+rs.getObject(7).toString()+"</td>");
    	 out.println("<td>"+rs.getObject(8).toString()+"</td>");
    	 out.println("</tr>");
    	 
    	 out.println("</tbody>");
    	 out.println("<tfoot>");
    	 out.println("<tr>");
    	 out.println("<td colspan='2'></td>");
    	 out.println("<td>"+"Balance: OMR "+rs.getObject(8).toString()+"</td>");
    	 out.println("</tr>");
    	 out.println("</tfoot>");
    	 out.println("</table>");
    	 out.println("</div>");
    	 
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
	        
         //temp ="index.html";  	
      	
      }
      
      //res.sendRedirect(temp);  
      out.println("</div>");
      out.println("</div>");
      out.println("</section>");
      out.println("<footer id='footer'>");
      out.println("<div class='inner'>");
      out.println("<div class='content'>");
      out.println("<section>");
      out.println("<h3>Why we are different</h3>");
      out.println("<p>As a constantly evolving species we are constantly looking for ways to improve our life by creating ");
      out.println("efficient and reliable technology, since we have reached a state where all data is in the palm of our ");
      out.println("hands there is no excuse to have a mediocre experience accessing our bank accounts and using all the ");
      out.println("features offered with absolute ease.</p>");
      out.println("</section>");
      out.println("<section>");
      out.println("<h4>Links</h4>");
      out.println("<ul class='alt'>");
      out.println("<li><a href='#'>Contact Us</a></li>");
      out.println("<li><a href='#'>Find Us.</a></li>");
      out.println("<li><a href='#'>Feedback.</a></li>");
      out.println("<li><a href='#'>Privacy.</a></li>");
      out.println("</ul>");
      out.println("</section>");
      out.println("<section>");
      out.println("<h4>Social</h4>");
      out.println("<ul class='plain'>");
      out.println("<li><a href='#'><i class='icon fa-twitter'>&nbsp;</i>Twitter</a></li>");
      out.println("<li><a href='#'><i class='icon fa-facebook'>&nbsp;</i>Facebook</a></li>");
      out.println("<li><a href='#'><i class='icon fa-instagram'>&nbsp;</i>Instagram</a></li>");
      out.println("<li><a href='#'><i class='icon fa-github'>&nbsp;</i>Github</a></li>");
      out.println("</ul>");
      out.println("</section>");
      out.println("</div>");
      out.println("<div class='copyright'>");
      out.println("&copy; Meesam. Majan College.");
      out.println("</div>");
      out.println("</div>");
      out.println("</footer>");
      out.println("<script src='assets/js/jquery.min.js'></script>");
      out.println("<script src='assets/js/browser.min.js'></script>");
      out.println("<script src='assets/js/breakpoints.min.js'></script>");
      out.println("<script src='assets/js/util.js'></script>");
      out.println("<script src='assets/js/main.js'></script>");
      out.println("</body>");
      out.println("</html>");
      
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