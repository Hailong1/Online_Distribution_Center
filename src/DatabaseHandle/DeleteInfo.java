package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class DeleteInfo extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	
	/**
	 * delete the information successfully and go back to the employee.
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
	   PrintWriter out;
	try {
		out = response.getWriter();
		out.println("<font size='8' color='pink'>delete accounts successfully</font>");
        out.println("<form action=\"EmployeePage.jsp\" method=\"POST\">");
		out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	/**
	 * delete the account when in the employee. 
	 */
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		print.pl("Ename:"+request.getParameter("3"));
		String name=request.getParameter("3");
		HttpSession session=request.getSession();
		String usename=(String)session.getAttribute("user");
	try {
			
		   if(name.equals(usename))
		  {
		     request.getRequestDispatcher("logoutA.jsp").forward(request,response);
		  }
	   }
       catch (ServletException e) 
       {
		// TODO Auto-generated catch block
		 e.printStackTrace();
       } catch (IOException e) 
       {
		// TODO Auto-generated catch block
		 e.printStackTrace();
       }	
		
			
		String select=null,result=null,delete=null,regionid=null;
		
		select="select * from customer where customer_username='"+name+"';";
		result=hd.getOneValue(select, 1);
		
		print.pl("regionid:"+regionid);
        if(result.equals("false")) //  in the employee.
        {    
        	select="select * from Employee where employee_username='"+name+"';";
        	delete="delete from warehouse where warehouse_managername='"+hd.getOneValue(select, 1)+"';";
        	hd.buildTable(delete);
        	// deleter the information from the warehouse information.
        	
          	
        	delete="delete from Employee where employee_id='"+hd.getOneValue(select, 1)+"';";//middle is 
        	                                                                                 // employee_id
        	print.pl("delete:"+delete);
        	hd.buildTable(delete);
        	if(!name.equals(usename))
        	{	
        	doGet(request,response);
        	}
        }
        else  // in the Customer
        {
        	delete="delete from product where product_customer='"+result+"';";
        	hd.buildTable(delete);
        	// deleter the information from the region information first.   
        	
        	delete="delete from Customer where customer_id='"+result+"';";// in this situation the 
        	                                                             //   result is the customer_id
            print.pl("delete:"+delete);
            hd.buildTable(delete);
            if(!name.equals(usename))
            {
            doGet(request,response);
            }
        }
	  
	
		
	}
  
}
