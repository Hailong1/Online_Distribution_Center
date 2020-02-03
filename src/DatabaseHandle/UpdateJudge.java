package DatabaseHandle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateJudge extends HttpServlet{
		
		private static final long serialVersionUID = 1L;
		PrintTo print=new PrintTo();
		Handle hd=new Handle();
		
		/**
		 *   directly to the update page in the customer.
		 */
		public void doGet(HttpServletRequest request,HttpServletResponse response)
		{
			String name=request.getParameter("2");
			request.setAttribute("changeC", name);
			print.pl(name);
			try {
				request.getRequestDispatcher("changeC.jsp").forward(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		/**
		 * judge the custumer or employee in the employee.
		 */
		public void doPost(HttpServletRequest request,HttpServletResponse response)
		{
			
			  String name=request.getParameter("4");
			  String select="select * from customer where customer_username='"+name+"';";
			  String result=hd.getOneValue(select, 1);
			  try {
				  
				 if(result.equals("false")) // not in the customer
				 {	
					request.setAttribute("Employeename", name);	 
				    request.getRequestDispatcher("updateE.jsp").forward(request,response);
				 }
				 else     // in the customer
				 {
					 request.setAttribute("Customername", name);
					 request.getRequestDispatcher("updateC.jsp").forward(request,response); 
				 }
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		
}
