<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="DatabaseHandle.*" import="java.util.List"
    import="java.util.ArrayList" import="java.util.Set" import="java.util.HashSet" import="java.util.Iterator"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>shopping</title>
<style type="text/css">
#title{
    font-size:50px;
    color:purple;
}
span{
  font-size:30px;
  color:blue;
}
#hre{
   color:red;
   font_size:40px;
}



</style>
<script type="text/javascript"> 



</script>
</head>
<body>
<% 
   new LoginServlet().Get(request, response); //judge if login
/*  if(judge.equals("true"))
  {
	 // return;
	  request.getRequestDispatcher("JudgeLogin.jsp").forward(request,response);
  }*/ 
  String select="select * from warehouse;";
  Handle hd=new Handle();
  ArrayList<String> list=new ArrayList<String>();
  list=hd.getOneListValue(select,2);
  System.out.println("list:"+list); 
 %>
<center>
<div id="title">
   welcome &nbsp employee &nbsp <%=session.getAttribute("user") %>
</div>
<div id="whole"></br>
       <div ><span> below is you function in warehouse: </span></div>
       <div id="add"></br>
       <form id="add1" action="addWinfo.jsp" method="POST">
       <span>1. you can add you warehouse information:  </span>
       <input type="submit" id="add1.1" name="1" value="add">
       </form>
       </div></br>
       <div id="view">
       <form id="view1" action="EviewA" method="POST">
       <span>2. view a products belong to and ship orders and choose a 
             warehouse: </span>
             <select id="view1.1" name="2">
             <%
              for(int i=0;i<list.size();i++){
             %>
            <option><%=list.get(i)%><%}%></option>
            </select> &nbsp&nbsp
            <input type="submit" value="viewproducts" name="3">
       </form>       
       </div></br>
       <div id="veiwall">
       <form id="veiwall1" action="viewA" method="POST">
       <span>3. view all of your warehouse information :</span>
          <input type="submit" value="viewallWarehouse" name="4">
       </form>
       </div></br>
        <div id="back">
        <form id="back1.1" action="EmployeePage.jsp" method="POST">
          <input type="submit" style="font-size:20px;" value="back" name="5">&nbsp&nbsp&nbsp&nbsp
          </span><a id="view.1" href="logout.jsp">
                   <span id="hre">logout</span></a> 
        </form>
        
        </div>







</div>
</center>
</body>
</html>