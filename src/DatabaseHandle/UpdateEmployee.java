package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateEmployee extends HttpServlet{
		
		private static final long serialVersionUID = 1L;
		PrintTo print=new PrintTo();
		Handle hd=new Handle();
		RegisterServlet rs=new RegisterServlet();
	    UpdateCustomer uc=new UpdateCustomer();
		
	    /**
		 * going back to EmployeePage 
		 */
		public void doGet(HttpServletRequest request,HttpServletResponse response)
		{
			 PrintWriter out;
				try {
					out = response.getWriter();
					out.println("<font size='8' color='orange'>update employee accounts successfully</font>");
			        out.println("<form action=\"EmployeePage.jsp\" method=\"POST\">");
					out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		/**
		 * update the information of employee
		 */
		public void doPost(HttpServletRequest request,HttpServletResponse response)
		{
			   String region=request.getParameter("6");
			   String username=request.getParameter("8");
			   print.pl("user:"+username);
			   region=rs.change(region);
			   uc.controlRegion(region,username,"Employee","employee_region","employee_username");
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
			   if(!fname.isEmpty()&&judge(fname,username,5).equals("untrue"))
			   {
				  
				 hd.updateInfor("Employee","employee_firstname",fname,"employee_username",username);  
			   }
			   if(!lname.isEmpty()&&judge(lname,username,6).equals("untrue"))
			   {
				 hd.updateInfor("Employee","employee_lastname",lname,"employee_username",username);
			   }
			   if((!s1.isEmpty())&&(!s2.isEmpty())&&judge(email,username,8).equals("untrue"))
			   {
			   hd.updateInfor("Employee","employee_emil",email,"employee_username",username);
			   }
			   if(!phone.isEmpty()&&judge(phone,username,7).equals("untrue"))
			   {
			   hd.updateInfor("Employee","employee_phone",phone,"employee_username",username);
			   }
			   doGet(request,response);
			 
		}
		
		/**
		 * judging if need to udpate. 
		 * @param value
		 * @return
		 */
		public String judge(String value,String username,int column)
		{
			String result=null;
			String select="select * from Employee where employee_username='"+username+"';";
			String result1=hd.getOneValue(select, column);
			if(value.equals(result1))
			{
				result="true";     //two value equal
			}
			else
			{
				result="untrue";   //two value not equal
			}
			return result;
		}
		
		
		
}
