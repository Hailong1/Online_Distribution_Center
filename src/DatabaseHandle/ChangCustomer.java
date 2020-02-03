package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangCustomer extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	RegisterServlet rs=new RegisterServlet();
	UpdateCustomer us=new UpdateCustomer();
	
	/**
	 * going back to EmployeePage 
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		   PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<font size='8' color='green'>update your accounts successfully</font>");
		        out.println("<form action=\"CustomerPage.jsp\" method=\"POST\">");
				out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	}
	
	
	/**
	 * update the information of customer except region_id and password.
	 */
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		   String region=request.getParameter("6");
		   String username=request.getParameter("8");
		   region=rs.change(region);
		   print.pl("region1;"+region);
		   us.controlRegion(region,username,"Customer","customer_region","customer_username");
		   String fname=request.getParameter("3");
		   fname=rs.change(fname);
		   String lname=request.getParameter("4");
		   lname=rs.change(lname);
		   String s1=request.getParameter("Cemail-first");
		   s1=rs.change(s1);
		   String s2=request.getParameter("Cemail-second");
		   s2=rs.change(s2);
		   String email=s1+"@"+s2;
		   String phone=request.getParameter("5");
		   phone=rs.change(phone);
		   String address=request.getParameter("7");
		   address=rs.change(address);
		   if(!fname.isEmpty()&&us.judge(fname,username,5,"Customer","customer_username").equals("untrue"))
		   {
			  
			 hd.updateInfor("Customer","customer_firstname",fname,"customer_username",username);  
		   }
		   if(!lname.isEmpty()&&us.judge(lname,username,6,"Customer","customer_username").equals("untrue"))
		   {
			 hd.updateInfor("Customer","customer_lastname",lname,"customer_username",username);
		   }
		   if((!s1.isEmpty())&&(!s2.isEmpty())&&us.judge(email,username,7,"Customer","customer_username").equals("untrue"))
		   {
		   hd.updateInfor("Customer","customer_email",email,"customer_username",username);
		   }
		   if(!phone.isEmpty()&&us.judge(phone,username,8,"Customer","customer_username").equals("untrue"))
		   {
		   hd.updateInfor("Customer","customer_phone",phone,"customer_username",username);
		   }
		   if(!address.isEmpty()&&us.judge(address,username,10,"Customer","customer_username").equals("untrue"))
		   {
		   hd.updateInfor("Customer","customer_address",address,"customer_username",username);
		   }
		   doGet(request,response);
		   
	}

}
