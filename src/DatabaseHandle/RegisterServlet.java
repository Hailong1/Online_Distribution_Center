package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet{

		private static final long serialVersionUID = 1L;
		PrintTo print=new PrintTo();
		Handle hd=new Handle();
		private Random rand=new Random();
		private Random random=new Random();
		
		/**
		 * 
		 */
	    public void doGet(HttpServletRequest request,HttpServletResponse response){   	
	  
	    }
	    
	    /**
	     * register the information. 
	     */
	    public void doPost(HttpServletRequest request,HttpServletResponse response){
	    	
	    	response.setContentType("text/html");
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	HttpSession session=request.getSession();
	    try {
	            String user=request.getParameter("0");
	            user=change(user);
	   		    String pwd=request.getParameter("1");
	            pwd=change(pwd);
	   		    String Fname=request.getParameter("3");
	   		    Fname=change(Fname);
	   		    String Lname=request.getParameter("4");
	   		    Lname=change(Lname);
	   		    String s1=request.getParameter("Cemail-first");
	   		    String s2=request.getParameter("Cemail-second");
	   		    String email=s1+"@"+s2;
	   		    email=change(email);
	   		    String Cphone=request.getParameter("5");
	   		    Cphone=change(Cphone);
	   		    String Cregion=request.getParameter("6");
	   		    Cregion=change(Cregion);
	   		    String Caddress=request.getParameter("7");
	   		    Caddress=change(Caddress);
	   		    String role=request.getParameter("8");
	   		    role=change(role);
	   		    String Cbirthday=request.getParameter("9");
	   		    Cbirthday=change(Cbirthday);
	   		    String balance=request.getParameter("22");
	   		    balance=change(balance);
	   		    int temp=97;
			    String cId="";
			    cId=cId+ (char)(rand.nextInt(26) + temp);
			    cId=cId+String.valueOf(rand.nextInt(10000));
			    String regionid=String.valueOf(rand.nextInt(10000));			    
			    byte[] saltBytes = new byte[16];
			    random.nextBytes(saltBytes);
	            String usersalt = hd.encodeHex(saltBytes, 32); // hash salt
			    String passhash = hd.getHash(pwd, usersalt);
	            print.pl("1:"+user);
	   		    print.pl("2:"+pwd);
	     		print.pl("3:"+Fname);
	   		    print.pl("4:"+Lname);
	   		    print.pl("5:"+email);
	   		    print.pl("6:"+Cphone);
	   		    print.pl("7:"+Cregion);
	   		    print.pl("8:"+Caddress);
	   		    print.pl("role:"+role);
	   		    print.pl("0:"+cId);
	   		    print.pl("10:"+Cbirthday);
	   		    print.pl("11:"+balance);
	   		    String select2="select * from region where region_name='"+Cregion+"';";
                String resultexist2=hd.handleRegionSql(select2, Cregion);
                print.pl("resultRegion:"+resultexist2);
	   		    StringBuffer br=new StringBuffer();
	   		    br.append("insert into customer values(");
	   		    br.append("'"+cId+"',");
	   		    br.append("'"+user+"',");
	   		    br.append("'"+passhash+"',");
	   		    br.append("'"+usersalt+"',");
	   		    br.append("'"+Fname+"',");
	   		    br.append("'"+Lname+"',");
	   		    br.append("'"+email+"',");
	   		    br.append("'"+Cphone+"',");
	   		    if(resultexist2.equals("false"))
	   		    {
	   		      br.append("'"+regionid+"',");
	   		    }
	   		    else 
	   		    {
	   		      br.append("'"+resultexist2+"',");	
	   		    }
	   		    br.append("'"+Caddress+"',");
	   		    br.append("'"+Cbirthday+"',"); 
	   		    br.append("'"+balance+"');");
	   		    String insertCustomer=br.toString();
	   		    print.pl("insertCustomer:"+insertCustomer);
	   		    br.setLength(0); 
	   		    br.append("insert into region values(");
	   		    br.append("'"+regionid+"',");
	   		    br.append("'"+Cregion+"');");
	   		    String insertRegion=br.toString();
	   		    br.setLength(0);
	   		    br.append("insert into employee values(");
	   		    br.append("'"+cId+"',");
	   		    br.append("'"+user+"',");
	   		    br.append("'"+passhash+"',");
	   		    br.append("'"+usersalt+"',");
	   		    br.append("'"+Fname+"',");
	   		    br.append("'"+Lname+"',");
	   		    br.append("'"+Cphone+"',");
	   		    br.append("'"+email+"',");
	   		    if(resultexist2.equals("false"))
	   		    {	
	   		    br.append("'"+regionid+"');");
	   		    }
	   		    else
	   		    {
	   		    	br.append("'"+resultexist2+"');");	
	   		    }
	   		    String insertEmployee=br.toString();	   		    
	   		    print.pl("insertRegion:"+insertRegion);  
	   		    print.pl("insertCustomer:"+insertCustomer);
	   		    print.pl("insertEmployee:"+insertEmployee);
	   		    boolean resultpass=Judgepass(pwd);
	   		    String select="select *from customer where customer_username='"+user+"';";
		   		String resultexist=hd.handleUseSql(select, user);
	   		    String select1="select *from employee where employee_username='"+user+"';";
	   		    String resultexist1=hd.handleUseSql(select1, user);
	   		  
	   		    if(!resultpass)
	   		    {
	   		    	request.getRequestDispatcher("PasswordError.jsp").forward(request,response);
	   		    }
	   		    else if(resultexist=="true"||resultexist1=="true"||user.isEmpty())
	   		    {
	   		    	request.getRequestDispatcher("UsernameIsExist.jsp").forward(request,response);
	   		    }
	   		   
	   		    else 
	   		    {	  
	   		    	session.setAttribute("id", cId);
	    		    session.setAttribute("user", user);
	    		    if(role.equals("Customer"))
	    		    {	
	    	//	    request.getRequestDispatcher("CustomerPage.jsp").forward(request,response);
	    		    request.getRequestDispatcher("Shopping.jsp").forward(request,response);	
	    		        if(resultexist2.equals("false"))
	    		        {	   
	    		           hd.buildTable(insertRegion);
	    		        }
	    		    hd.buildTable(insertCustomer);
	    		    }
	    		    if(role.equals("employee"))
	    		    {
	    		//    	request.getRequestDispatcher("EmployeePage.jsp").forward(request,response);
	    		    	 request.getRequestDispatcher("Shopping.jsp").forward(request,response);		
	    		    	  if(resultexist2.equals("false"))
	    		    	  { 
	    		    		  hd.buildTable(insertRegion);
	    		    	  }
	 	    		    hd.buildTable(insertEmployee);
	    		    }
	    		    
	   		    }
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	
	    }
	    
	    /**
	     * 
	     * @param p
	     * @return
	     */
	    public boolean Judgepass(String pw)
	    {
	    	boolean flag=false;
	    	String reglex="[A-Z]+";
	    	String reglex1="[0-9]+";
	    	String reglex2="_+"; // must contain "_ "; 
	    	String reglex3="[a-z]+";
	    	Pattern p= Pattern.compile(reglex);
	    	Matcher m = p.matcher(pw);
	    	Pattern p1= Pattern.compile(reglex1);
	    	Matcher m1 = p1.matcher(pw);
	    	Pattern p2= Pattern.compile(reglex2);
	    	Matcher m2 = p2.matcher(pw);
	    	Pattern p3= Pattern.compile(reglex3);
	    	Matcher m3 = p3.matcher(pw);
	    	if(m.find()&&m1.find()&&m2.find()&&pw.length()>=8&&m3.find())
	    	{
	    		flag=true;
	    	}
	        return flag;
	    	
	    }
	    
	    /**
	     *  
	     * @param s
	     * @return
	     */
	    public String change(String s)
	    
	    {
	    	 s=s.replaceAll(",", " ");
	   		 s=s.replaceAll("\'", " ");
	   		 s=s.trim();//get rid of "".
	    	return s; 
	    }
	    
	  
	   
		
		

}
