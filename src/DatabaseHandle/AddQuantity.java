package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddQuantity extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		PrintWriter out;
		try {
			out=response.getWriter();
			out.println("<font size='8' color='green'>update quantity successfully</font>");
	        out.println("<form action=\"CustomerPage.jsp\" method=\"POST\">");
			out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * update the quantity of products
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		String product_id=request.getParameter("3");
		String quantity=request.getParameter("1");
	    String update=null;
	    update="update product set product_quantity='"+quantity+"' where product_id='"+product_id+"';";
	    print.pl("updateQuantity:"+update);
	    hd.buildTable(update);
	    doGet(request,response);
	}

}
