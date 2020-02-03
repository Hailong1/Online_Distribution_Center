<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="DatabaseHandle.*" import="java.util.List"
    import="java.util.ArrayList" import="java.util.Set" import="java.util.HashSet" import="java.util.Iterator"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>update customer</title>
</head>
<body>

<% 
  new LoginServlet().Get(request, response);
  /*if(judge.equals("true"))
  {
	  return;
  } */
  Handle hd=new Handle();
  String name="null";
  name=request.getParameter("4");
  String select="select * from customer where customer_username='"+name+"';";
  ArrayList<String> list=new ArrayList<String>(); 
  list=hd.getWholeOneListValue(select,11);
  String []mail=list.get(6).split("@");
  String Fmail=mail[0];
  String Lmail=mail[1];
  select="select * from region where region_id='"+list.get(8)+"';";
  String result=hd.getOneValue(select,2);
 %>

<style type="text/css">

 #ta{
     border-style:solid;
     border-color:black;
     height:350px;
     width:700px;
 }
 input{
    border-top:none;
    border-left:none;
    border-right:none;
    border-bottom-color:green;
    background-color: yellow;
}

#title{
       color:red;
       font-size:50px;
 }
 
 #d{
      color:red;
      font-size:30px;
 }
 #td{
    color:blue;
    font-size:20px;
    font-weight: bold;
 }
</style>
<script type="text/javascript">
     function update ()
     {
  	   var phone=document.getElementById("in7").value;
  	   if(phone.length<10)
  	   {
  		  alert("please enter at least ten number for phone");   
  		  return;
  	   }
  	   if(isNaN(phone))
  	   {
  		   alert("please enter at least ten number for phone");   
  		   return;  
  	   } 
  	  else
  	  {document.getElementById("fom").submit();
  	   }
    	 
    	 
     }


</script>
</head>
<body><center>


    <div id="all">
    <div id="title">update customer</div><br/>
    <form id="fom" action="cinfor" method="POST">
       <table id="ta">
           <tr id="tr4">
               <td id="td">First-name:&nbsp&nbsp&nbsp</td>
               <td id="td4.2"><input type="text" id="in4" value="<%=list.get(4)%>" name="3"></td>
           </tr>
           <tr id="tr5">
               <td id="td">Last-name:&nbsp&nbsp&nbsp</td>
               <td id="td5.2"><input type="text" id="in5" value="<%=list.get(5)%>" name="4"></td>
           </tr>
           <tr id="tr6">
               <td id="td">Email:&nbsp&nbsp&nbsp</td>
               <td id="td6.2"><input type="text" id="in6" value="<%=Fmail%>"name="Cemail-first">
               @<input type="text" value="<%=Lmail%>"name="Cemail-second">
           </tr>
           <tr id="tr7">
               <td id="td">phone:&nbsp&nbsp&nbsp</td>
               <td id="td7.2"><input type="text" id="in7" value="<%=list.get(7)%>" name="5"></td>
           </tr>
            <tr id="tr8">
               <td id="td">region:&nbsp&nbsp&nbsp</td>
               <td id="td8.2"><input type="text" id="in8" value="<%=result%>"name="6"></td>
           </tr>
            <tr id="tr9">
               <td id="td">address:&nbsp&nbsp&nbsp</td>
               <td id="td9.2"><input type="text" id="in9" value="<%=list.get(9)%>"name="7"></td>
         <table>
         <p id="p">
         <input type="hidden" name="8" value="<%=request.getAttribute("Customername") %>">
         <input type="reset" value="reset" style="font-size:20px;">
             <input type="button" value="update" style="font-size:20px;" onclick="update()" >
        </form> 
        
        
      
     </center>   
    </p>
   
    </div>
    <div>
     <form action="EmployeePage.jsp">
     <input type="submit" value="back" style="font-size:30px;">
     </form>
     </div>


</body>
</html>