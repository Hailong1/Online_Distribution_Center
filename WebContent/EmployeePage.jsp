<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="DatabaseHandle.*" import="java.util.List"
    import="java.util.ArrayList" import="java.util.Set" import="java.util.HashSet" import="java.util.Iterator"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>shopping</title>
</head>
<body>
<title>shopping</title>
<style type="text/css">
#title{
  font-size: 50px;
  color:red;
}
span{
  color:blue;
  font-size:30px;
}
#view.1{
   color:red;
   font-size:30px;
}
#des{
   color:blue;
   font_size:30px;
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
 new LoginServlet().Get(request, response);
 /* if(judge.equals("true"))
  {
	  return;
  } */
  ArrayList<String> list=new ArrayList<String>();
  ArrayList<String> list1=new ArrayList<String>();
  String select="select * from employee;";                        		        
  list=new Handle().getOneListValue(select,2);
  for(int j=0;j<list.size();j++)
  {
  	list1.add(list.get(j));
  }
  select="select * from customer;";
  list=new Handle().getOneListValue(select,2);
  
  for(int k=0;k<list.size();k++)
  {
  	list1.add(list.get(k));
  }
%>
<center>
<div id="title">
   welcome &nbsp employee &nbsp <%=session.getAttribute("user") %>
</div>
     </br> <div id="des"> below is you function please choose one:</div></br>
            <div id="add"> 
            <form id="add1" action="AddNewRole.jsp" method="POST"><span>
                   1. choose new role you want to add new information: </span>&nbsp&nbsp
              &nbsp<input type="submit" id="add1.2" name="2" style="font-size:20px;" value="AddSubmit">
            </form> 
            </div></br> 
            <div id="delete">
            <form id="delete1" action="dinfo" method="POST">
                  <span> 2. choose a role name you want to delete:  
                     name: </span><select id="delete1.2" name="3">
                   <%  for(int i=0;i<list1.size();i++){
                    %><option> <%=list1.get(i)%><%}%></option>
                     </select>&nbsp&nbsp
                    <input type="submit" id="delete1.3" style="font-size:20px;"value="deleteSubmit">      
            </form>           
            </div></br> 
            <div id="update">
                 <form id="undate1" action="a" method="POST"><span>
                   3. &nbsp choose a role name you want to update:  
                     name: </span> <select id="update1.2" name="4">
                   <%  for(int i=0;i<list1.size();i++){
                    %><option> <%=list1.get(i)%><%}%></option>
                     </select>&nbsp&nbsp
                    <input type="submit" id="update1.3" style="font-size:20px;"value="updateSubmit">  
               </form>      
             </div></br>
              <div id="view"><span>
                   4.view all information in your warehouse:  </span><a id="view.1" href="WareHouse.jsp">
                   <span id="hre">WareHouse</span></a> 
              </div></br></br>
              <div id="logout">
                    <form  id="logout1" action="logout.jsp" method="POST">
                   <input type="submit" id="logout1.1" style="font-size:20px;" value="logout">
                   </form>
              </div>
             
             
            
            
             
               
</div>
</center>
</body>
</html>