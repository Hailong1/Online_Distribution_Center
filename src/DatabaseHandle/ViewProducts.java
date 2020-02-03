package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.Response;

public class ViewProducts extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	
	/**
	 *  change the shop orders, every time is minus one and store in database.
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		String userid=(String)session.getAttribute("id");
		print.pl("userid:"+userid);
		//change the balance.
		String product_id=request.getParameter("10");
	    String select=null,update=null;
	    int number=0;
	    select="select * from product where product_id='"+product_id+"';";
	    String intev=hd.getOneValue(select, 8);
	 //   String price=hd.getOneValue(select, 7);//get the price
	 //   double priceC=Double.parseDouble(price);
	//    Cbalance(userid,priceC);
	    number=Integer.parseInt(intev);
	    number=number-1;
	     //finish a ship so minus one. 
	    String quantity=Integer.toString(number);
	    print.pl("quantity:"+quantity);
	    PrintWriter out;
	    
		try {
			out=response.getWriter();
			if(number>=0) // have this products.
			{
		    out.println("<font size='8'color='blue'>ship products successfully "
				+ "and quantity number will minus one</font>");
	        out.println("<form action=\"WareHouse.jsp\" method=\"POST\">");
			out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
			
				update="update product set product_quantity='"+quantity+"' where product_id='"+product_id+"';";
				
				hd.buildTable(update);
				//update="update customer set product_balance='"+list.get(2)+"'where.............";
				//hd.buildTable(update);
				print.pl("update: "+update);
			}
			else // do not have products
			{
				out.println("<font size='8'color='red'>quantity of this quantity is 0 so can not ship so fail</font>");
			    out.println("<form action=\"WareHouse.jsp\" method=\"POST\">");
			    out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		
	}
	
	
	/**
	 *view the products belong to a warehouse that you choose
	 */
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		HttpSession session=request.getSession();
		String userid=(String)session.getAttribute("id");
		String username=(String)session.getAttribute("user");
		String warehouse=request.getParameter("2");
//		print.pl("useid :"+userid);
		String select=null;
		String inter=null;
		PrintWriter out;
	
		try {
			out = response.getWriter();
			out.println("Hello <font color=\"red\">"+username+"</font> the whole products of yours</br>");
	
			select="select * from warehouse where warehouse_name='"+warehouse+"'"
					+ "and warehouse_managername='"+userid+"';";
			inter=hd.getOneValue(select, 3);
			// get the warehouse_regin_id from 
			print.pl("first select:"+select);
			select="select * from product where product_region="+
					"(select warehouse_region from warehouse where warehouse_region='"+inter+"');";
			print.pl("second select:"+select);
			String select1="select * from customer where customer_id='"+userid+"';";
			print.pl("third select:"+select1);
			String result=hd.allInforE(select,select1);
			print.pl("result"+result);
			out.println(result);
//			out.println("<form action=\"EviewA\" method=\"GET\">");
//			out.println("<input type=\"submit\" value=\"ship_orders\"  style=\"font-size:20px;\"></form>");
			out.println("<form action=\"WareHouse.jsp\" method=\"POST\">");
			out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
	/**
	 * update the balance
	 * @param userid
	 */
	public void Cbalance(String userid,double price)
	{
		String select="select * from Customer where customer_id='"+userid+"';";
		String bala=hd.getOneValue(select, 12);
		print.pl("bla:"+bala);
		double balaC=Double.parseDouble(bala);
		balaC=balaC-price;
		String s=Double.toString(balaC);
		String update="update Customer set customer_balance='"+s+"' where customer_id='"+userid+"';";
		print.pl("updated balance:"+update);
		hd.buildTable(update);
		
	}
}
