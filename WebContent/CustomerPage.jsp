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
  color:#ff0000;
  font-size: 50px;
}
span{
  color:green;
  font-size:30px;
}
#href{
  color:red;
  font-size:30px;
}
input
{
  font-size:30px;
  color:pink;
}
</style>
<script type="text/javascript"> 

</script>

</head>
<body>
<%   
  new LoginServlet().Get(request, response);
  /*if(judge.equals("true"))
  {
	  return;
  }*/ 
  
  String select="select * from category;";
  ArrayList<String> list=new ArrayList<String>();
  list=new Handle().getOneListValue(select,2);
  //System.out.println(select);
  
  %>
 <center>
<div id="title">
   welcome &nbsp customer &nbsp <%=session.getAttribute("user") %>
</div>
<div id="whole"></br>
            <div> <span>  below is you function please choose one: </span> </div></br> 
             <div id="update">
               <form id="update1" action="a" method="GET">
                 <span>1. you can update you information: </span>
                <input type="submit" id="update1.1" name="1", value="Updateinfo" sytle="font-size=20px">
                <input type="hidden" value="<%=session.getAttribute("user")%>" name="2">
              </form>
               </div></br>  
               <div id="product">
                <form id="product2" action="product" method="POST">
                <span>2. you can upload production information: </span>
                 <input type="submit" id="product2.1" name="3" value="update_product" sytle="font-size=20px">
                 <input type="hidden" value="<%=session.getAttribute("user")%>" name="4">
                </form> 
               </div></br>
               <div id="category1">
                    <form id="category1.3" action="categoryS" method="GET">
                    <span>3. you can choose one category to show the whole products of this category
                       category</span>
                        <select id="category1.4", name="5">
                        <%
                          for(int i=0;i<list.size();i++){
                        %>
                        <option><%=list.get(i)%><%}%></option>
                        </select> &nbsp&nbsp
                        <input type="submit" id="category1.5" name="6" value="showProduct">
                    </form> 
               </div> </br>
               <div id="category2">
                   <form id="category2.4" action="categoryS" method="POST">
                   <span>4. you can add a new category:  category </span>
                   <input type="text" id="category2.5" name="7">
                   &nbsp&nbsp <input type="submit" id="category2.6" name="8" value="addCategory">
                   </form>
              </div></br>
              <div id="logout">
                    <form  id="logout1" action="logout.jsp" method="POST">
                   <input type="submit" id="logout1.1" style="font-size:20px;" value="logout">
                   </form>
              </div>
             


</div>
</center>
</body>
</html>