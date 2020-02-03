package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewWareHouse extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	
	/**
	 * 
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		
	}
	
    /**
     * show all warehouse.
     */
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		
		String WMname=(String)session.getAttribute("id");
		print.pl("id:"+WMname);
		String select="select * from warehouse where warehouse_managername='"+WMname+"';";
		String result=null;
		result=hd.allInforEW(select);
		PrintWriter out;
		try {
			out=response.getWriter();
			out.println("<font color=\"blue\"> below is you all warehouse and each one choose show pruduct"
					+ " to look the pruducts that in this warehouse.</font>");
			out.println(result);
			print.pl("result:"+result);
			out.println("<form action=\"WareHouse.jsp\" method=\"POST\">");
			out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
