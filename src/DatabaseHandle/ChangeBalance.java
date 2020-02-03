package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeBalance extends HttpServlet{

	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		PrintWriter out;
		try {
			out=response.getWriter();
			out.println("<font size='8' color='red'>buy products sucessfully</font>");
	        out.println("<form action=\"CustomerPage.jsp\" method=\"POST\">");
			out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * change the balance.
	 * @param request
	 * @param response
	 */
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		String id=request.getParameter("10");
		HttpSession session=request.getSession();
		String userid=(String)session.getAttribute("id");
		print.pl("id"+id);
		print.pl("userid"+userid);
		String select="select * from product where product_id='"+id+ "';";
		print.pl("select     "+select);
		String price=hd.getOneValue(select, 7);
		String quantity=hd.getOneValue(select, 8);
		int quan=Integer.parseInt(quantity);
		int quanJ=quan;
		
	//	hd.buildTable(update);
		
		double priceP=Double.parseDouble(price);
		
		if(quanJ==0)
		{
			PrintWriter out;
			try {
				out=response.getWriter();
				out.println("<font size='8' color='red'> out of stock sucessfully so fail</font>");
		        out.println("<form action=\"CustomerPage.jsp\" method=\"POST\">");
				out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
		quan--;
	    String update="update product set product_quantity='"+quan+"' where product_id='"+id+"';";	
	    print.pl("updated quantity:"+update);
	    hd.buildTable(update);	
	    new ViewProducts().Cbalance(userid, priceP);
		doGet(request,response);
		}
		
	}
	

}
