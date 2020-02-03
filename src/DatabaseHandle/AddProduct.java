package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddProduct extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	RegisterServlet rs=new RegisterServlet();
	private Random rand=new Random();
	
	/**
	 * show the result and return to customer image.
	 * @param request
	 * @param response
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
	
		 PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<font size='8' color='green'>update product successfully</font>");
		        out.println("<form action=\"CustomerPage.jsp\" method=\"POST\">");
				out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	

	/**
	 * add information into database in the products tables
	 * @param request
	 * @param response
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		String customer=(String)session.getAttribute("id"); //get the id.
		print.pl("customer_id:"+customer);
		String data=request.getParameter("1");
		String []part=data.split(",");
		part[0]=part[0].substring(1, part[0].length());
		part[part.length-1]=part[part.length-1].substring(0,part[part.length-1].length()-1);
		//get rid of the "]". 
		 part[part.length-1]="http://localhost:8088/DataBase/actproduct/"+part[part.length-1].trim();
		// get the whole link of the products.
		for(int i=0;i<part.length;i++)
		{
		    part[i]=rs.change(part[i]);
		   	print.pl("link "+i+":"+part[i]);
		}
		String regionid=String.valueOf(rand.nextInt(10000));
		String productid=String.valueOf(rand.nextInt(10000));
		String categoryid=String.valueOf(rand.nextInt(10000));
	    String select=null,insert=null;
	    select="select * from region where region_name='"+part[8]+"';";
	    print.pl("select_product:"+select);
		String result=hd.handleRegionSql(select, regionid);   // judge region at the table category.
		select="select * from category where category_name='"+part[1]+"';";
		print.pl("select_category:"+select);
		String resultC=hd.handleRegionSql(select, regionid); //judge category at the table category
		StringBuffer br=new StringBuffer();
		if(result.equals("false")) //products region without in region.
		{
			if(resultC.equals("false"))  //products category without in category
			{
			insert="insert into region values('"+regionid+"','"+part[8]+"');";
			print.pl("insertR: "+insert);
			hd.buildTable(insert);
			insert="insert into category values('"+categoryid+"','"+part[1]+"');";
			print.pl("insertC: "+insert);
			hd.buildTable(insert);
			br.append("insert into product values(");
			br.append("'"+productid+"',");
			br.append("'"+part[0]+"',");
			br.append("'"+part[9]+"',");
			br.append("'"+categoryid+"',");
			br.append("'"+part[2]+"',");
			br.append("'"+part[3]+"',");
			br.append("'"+part[4]+"',");
			br.append("'"+part[5]+"',");
			br.append("'"+part[6]+"',");
			br.append("'"+part[7]+"',");
			br.append("'"+part[10]+"',");
			br.append("'"+customer+"',");
			br.append("'"+regionid+"');");
			insert=br.toString();
			br.setLength(0);
			print.pl("insertP"+insert);
			hd.buildTable(insert);
			}
			else                      //products category in the category.
			{
				insert="insert into region values('"+regionid+"','"+part[8]+"');";
				print.pl("insertR: "+insert);
				hd.buildTable(insert);
				br.append("insert into product values(");
				br.append("'"+productid+"',");
				br.append("'"+part[0]+"',");
				br.append("'"+part[9]+"',");
				br.append("'"+resultC+"',");
				br.append("'"+part[2]+"',");
				br.append("'"+part[3]+"',");
				br.append("'"+part[4]+"',");
				br.append("'"+part[5]+"',");
				br.append("'"+part[6]+"',");
				br.append("'"+part[7]+"',");
				br.append("'"+part[10]+"',");
				br.append("'"+customer+"',");
				br.append("'"+regionid+"');");
				insert=br.toString();
				br.setLength(0);
				print.pl("insertP"+insert);
				hd.buildTable(insert);
			}
				
		}
		else          //  products region in the region.
		{
			if(resultC.equals("false"))  //products category without in category
			{
			insert="insert into category values('"+categoryid+"','"+part[1]+"');";
			print.pl("insertC: "+insert);
			hd.buildTable(insert);
			br.append("insert into product values(");
			br.append("'"+productid+"',");
			br.append("'"+part[0]+"',");
			br.append("'"+part[9]+"',");
			br.append("'"+categoryid+"',");
			br.append("'"+part[2]+"',");
			br.append("'"+part[3]+"',");
			br.append("'"+part[4]+"',");
			br.append("'"+part[5]+"',");
			br.append("'"+part[6]+"',");
			br.append("'"+part[7]+"',");
			br.append("'"+part[10]+"',");
			br.append("'"+customer+"',");
			br.append("'"+result+"');");
			insert=br.toString();
			br.setLength(0);
			print.pl("insertP"+insert);
			hd.buildTable(insert);
			}
			else   //products category in the category.
			{
				br.append("insert into product values(");
				br.append("'"+productid+"',");
				br.append("'"+part[0]+"',");
				br.append("'"+part[9]+"',");
				br.append("'"+resultC+"',");
				br.append("'"+part[2]+"',");
				br.append("'"+part[3]+"',");
				br.append("'"+part[4]+"',");
				br.append("'"+part[5]+"',");
				br.append("'"+part[6]+"',");
				br.append("'"+part[7]+"',");
				br.append("'"+part[10]+"',");
				br.append("'"+customer+"',");
				br.append("'"+result+"');");
				insert=br.toString();
				br.setLength(0);
				print.pl("insertP"+insert);
				hd.buildTable(insert);
			}
		}
		
		doGet(request,response);
			
	}
	
	
	

}
