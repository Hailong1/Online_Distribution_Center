package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * the process of login. 
 * @author Hailong
 *
 */
public class LoginServlet extends HttpServlet{
	

	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	
	/**
	 * judge if the use login or not. 
	 */
    public void Get(HttpServletRequest request,HttpServletResponse response){   
    	
    	HttpSession session=request.getSession();
 	    Object object=session.getAttribute("user");
 	    response.setContentType("text/html");
 //	    String result="false";
 //	    PrintWriter out;
 		try {
 	//		out = response.getWriter();
 			if(object==null){
 	//			result="true";
 				 request.getRequestDispatcher("JudgeLogin.jsp").forward(request,response);
 		//		out.println("you hava not login please login in");
 		//		out.println("<form action=\"Shopping.jsp\" method=\"POST\">");
 		//		out.println("<input type=\"submit\" value=\"login\" style=\"font-size:20px;\" ></form>");
 			}
 			
 		} catch (IOException | ServletException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 //	  return result;
  
    }
    
    
    /**
     * judge the login information is correct
     */
    public void doPost(HttpServletRequest request,HttpServletResponse response){
    	
    	response.setContentType("text/html");
    	response.setStatus(HttpServletResponse.SC_OK);
    	HttpSession session=request.getSession();
        try {
 //       	PrintWriter out=response.getWriter();
    		String usd=request.getParameter("user");
        	String pwd=request.getParameter("pass");
        	String role=request.getParameter("8");
        	print.pl("role:"+role);
        	print.pl("usd:"+usd);
        	print.pl("pass:"+pwd);
        	String select="select *from customer where customer_username='"+usd+"';";
        	String select1="select *from employee where employee_username='"+usd+"';";
        	String resultexist=null;
        	String id=null;
        	String selectid=null;
        	if(role.equals("Customer"))
        	{	
    		   resultexist=hd.login(select, usd,pwd);
    		   selectid="select * from Customer where customer_username='"+usd+"';";
        	}
        	if(role.equals("employee"))
        	{
        		resultexist=hd.login(select1, usd, pwd);
        		selectid="select * from Employee where employee_username='"+usd+"';";
        	}
    		if(resultexist=="true")
    		{
       		    id=hd.getOneValue(selectid,1);
    		    session.setAttribute("id", id);
    		    session.setAttribute("user", usd);
    		    String id1=(String)session.getAttribute("id");
    		    print.pl("id="+id1);
    		    if(role.equals("Customer"))
    		    {	
    		        request.getRequestDispatcher("CustomerPage.jsp").forward(request,response);
    		    }
    		    if(role.equals("employee"))
    		    {
    		    	request.getRequestDispatcher("EmployeePage.jsp").forward(request,response);
    		    }
    		}
    		else 
    		{
    			request.getRequestDispatcher("loginA.jsp").forward(request,response);
    		}
    		    
				
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    }


    
    
    
    
    
}
