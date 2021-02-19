

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String en = request.getParameter("uname");
		String pn = request.getParameter("pass");
		
//		if(un.equals("admin") && pn.equals("admin")) {
//			RequestDispatcher rd = request.getRequestDispatcher("MyServlet2");
//			rd.forward(request, response);
//			//out.println("This is my first output from Demo_Servlets in Browser ....! =======");
//		}
//		else {
//			out.println("Invalid username or password!!!");
//			RequestDispatcher rd = request.getRequestDispatcher("MyServlet3");
//			rd.forward(request, response);
//			//out.println("fail =======");
//		}
		System.out.println("username : "+en +" "+"password : "+pn);
		// load the driver
		try
		{
			// load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // loading driver step
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Likhila14*"); 
			// creating the statement
			PreparedStatement  pstmt= con.prepareStatement("select * from reg");
			int i = 0;
			ResultSet rs = pstmt.executeQuery();
			
				while (rs.next())
				{
					if(rs.getString(1).equals(en) && rs.getString(4).equals(pn)) {
						i = 1;
					}
				
					
					if(i == 1) {
						System.out.println(rs.getString(1)+" "+rs.getString(3));
						System.out.println("Login Successful");
					}else {
						System.out.println(rs.getString(1)+" "+rs.getString(3));
						System.out.println("Login failed");
					}
				}
				
				// close the connection
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{		
			System.out.println(e);  
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
