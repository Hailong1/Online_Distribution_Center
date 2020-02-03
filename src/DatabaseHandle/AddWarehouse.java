package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddWarehouse extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	RegisterServlet rs=new RegisterServlet();
	private Random rand=new Random();
	
	/**
	 * going back to he warehouse
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{ 
		 PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<font size='8' color='grey'>add warehouse successfully</font>");
		        out.println("<form action=\"WareHouse.jsp\" method=\"POST\">");
				out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
	
	/**
	 * add the information to the warehouse
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{ 
		     HttpSession session=request.getSession();
		     String user=(String)session.getAttribute("user");
		     String regionid=String.valueOf(rand.nextInt(10000));
		     String wareid=String.valueOf(rand.nextInt(10000));
             String name=request.getParameter("0");
             name=rs.change(name);
             
             String region=request.getParameter("1");
             region=rs.change(region);
             String address=request.getParameter("2");
             address=rs.change(address);
     //        print.pl("name:"+name);
     //        print.pl("region:"+region);
     //        print.pl("address:"+address);
     //        print.pl("new name:"+user);
             String select=null,insert=null;
             select="select * from region where region_name='"+region+"';";
             String result=hd.handleRegionSql(select, region); // get the region_id
             print.pl("region_id:"+result);
             select="select * from Employee where employee_username='"+user+"';";
             ArrayList<String> list=new ArrayList<String>();
             list=hd.getWholeOneListValue(select, 9);
             print.pl(list);
             StringBuffer br=new StringBuffer();
             if(result.equals("false")) //without the region
             {
            	 insert="insert into region values('"+regionid+"','"+region+"');";
            	 print.pl("insert:"+insert);
            	 hd.buildTable(insert);
            	 br.append("insert into warehouse values(");
            	 br.append("'"+wareid+"',");
            	 br.append("'"+name+"',");
            	 br.append("'"+regionid+"',");
            	 br.append("'"+list.get(0)+"',");
            	 br.append("'"+list.get(7)+"',");
            	 br.append("'"+list.get(6)+"',");
            	 br.append("'"+address+"');");
            	 insert=br.toString();
            	 br.setLength(0);
            	 print.pl("insert:"+insert);
            	 hd.buildTable(insert);
             }
             else   //in the region
             {
            	 br.append("insert into warehouse values(");
            	 br.append("'"+wareid+"',");
            	 br.append("'"+name+"',");
            	 br.append("'"+result+"',");
            	 br.append("'"+list.get(0)+"',");
            	 br.append("'"+list.get(7)+"',");
            	 br.append("'"+list.get(6)+"',");
            	 br.append("'"+address+"');");
            	 insert=br.toString();
            	 br.setLength(0);
            	 print.pl("insert:"+insert);
            	 hd.buildTable(insert);
             }
             
             doGet(request,response);
             
             
    }

}
