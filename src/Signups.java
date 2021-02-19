import java.io.*;
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*;
import java.util.*;
public class Signups extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Signups() {
        super();   
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("uname");
        String fullname = request.getParameter("fname");
       
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String cpassword = request.getParameter("cpass");
        System.out.println("recieved");
        
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","Likhila14*");
            PreparedStatement pstmt=con.prepareStatement("insert into reg values(?,?,?,?,?)");
            pstmt.setString(1, username);
            pstmt.setString(2, fullname);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, cpassword);
            boolean n = pstmt.execute();
            System.out.println(n);
            if(n)
            {
                System.out.println("One Record inserted successful");
            }
            else
            {
                System.out.println("One Record not inserted Successfully... ");
            }
        }
        catch(Exception e)
        {        
            System.out.println(e);  
        }
        
    }

 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        doGet(request, response);
    }

 

}