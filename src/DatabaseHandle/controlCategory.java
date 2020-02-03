package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class controlCategory extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	RegisterServlet rs=new RegisterServlet();
	private Random rand=new Random();
	
	/**
	 * the whole products of this category
	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		String userid=(String)session.getAttribute("id");
		String select1="select * from Customer where Customer_id='"+userid+"'";
		print.pl("balance:"+select1);
		String balance=hd.getOneValue(select1, 12);
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		try {
			String category=request.getParameter("5");
//			HttpSession session=request.getSession();
			String username=(String)session.getAttribute("user");
			PrintWriter out=response.getWriter();
			out.println("Hello <font color=\"red\">"+username+"</font> the whole products of <font color=\"red\"> "
			            +category+"</font> in yours is:</br>");
			out.println("And your balance is <font color=\"red\">"+balance+".</font></br>");
			String select=null;
			select="select * from product where product_customer="
					+ "(select customer_id from customer where customer_username='"+username+"')"
					+"and product_category=(select category_id from category where category_name='"+category+"');";
//			print.pl("whole select:"+select);
			String result=hd.allInfor(select);// get the whole infor. 
//			print.pl(result);
			if(result.isEmpty())
			{
				out.println("<font color=\"blue\">no products of in it.</font>");
				out.println("<form action=\"CustomerPage.jsp\" method=\"POST\">");
				out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
			}
			else
			{	
			out.println(result);
			out.println("<form action=\"CustomerPage.jsp\" method=\"POST\">");
			out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * add category"
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			PrintWriter out=response.getWriter();
			String category_name=request.getParameter("7");
			String select="select * from category where category_name='"+category_name+"';";
			String categoryid=String.valueOf(rand.nextInt(10000));
			String result=hd.handleRegionSql(select, categoryid);
			String insert=null;
			if(result.equals("false"))  // not exist the category.
			{
				out.println("<font  color=\"orange\">add category successfully</font>");
				out.println("<form action=\"CustomerPage.jsp\" method=\"POST\">");
				out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
				insert="insert into category values('"+categoryid+"','"+category_name+"');";
				hd.buildTable(insert);
				print.pl("insert:"+insert);
			}
			else      // in the category
			{
				out.println("<font size=\"8\" color=\"orange\">the category has exist, so you can not add</font>");
				out.println("<form action=\"CustomerPage.jsp\" method=\"POST\">");
				out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
