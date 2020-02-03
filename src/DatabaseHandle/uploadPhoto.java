package DatabaseHandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class uploadPhoto extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	PrintTo print=new PrintTo();
	Handle hd=new Handle();
	
	/**
	 * going to the upload the information
	 * @param request
	 * @param response
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		PrintWriter out;
		ArrayList<String> list=new ArrayList<String>();
		String imagelink=request.getParameter("10");
		for(int i=0;i<=10;i++)
		{
			String number=String.valueOf(i);
			list.add(request.getParameter(number));
		}
	
		try {
			out=response.getWriter();
			out.println("<font size='15' color='red'>your pruducts image is:</font>");
			out.println("<form action=\"actproduct\" method=\"POST\">");
			out.println("<input type=\"image\" src=\""+imagelink+"\"></br>");
			out.println("<input type=\"hidden\" value=\""+list+"\" name=\"1\">");
			out.println("<input type=\"submit\" value=\"addPicture\" name=\"2\" style=\"font-size:20px;\"></form>");
	        out.println("<form action=\"production.jsp\" method=\"POST\">");
			out.println("<input type=\"submit\" value=\"back\" style=\"font-size:20px;\" ></form>");
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	

	/**
	 * going to the production.jsp.
	 * @param request
	 * @param response
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			request.getRequestDispatcher("production.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * get the link of the update image url. 
	 * @return
	 */
	public ArrayList<String> geturl()
	{
		ArrayList<String> list=new ArrayList<String>();
		for(int i=11;i<99;i++)
		{
			i++;
			list.add(i+".jpg");
		}
		return list;
	}
	

}
