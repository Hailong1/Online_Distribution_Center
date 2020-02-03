<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="DatabaseHandle.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>add new role</title>
</head>
<body>
<%
 new LoginServlet().Get(request, response);
/*if(judge.equals("true"))
{
	  return;
} */

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
 
 span{
      color:red;
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
function register(){
	   var user=document.getElementById("in1").value;
	   var pass=document.getElementById("in2").value;
	   var Rpass=document.getElementById("in3").value;
	   var Cn=document.getElementById("in4").value;
	   var email=document.getElementById("in6").value;
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
	   if(user==""||pass==""||Rpass==""||Cn==""||email=="")
	   {
	      alert("the * can not be the empty.");
	      return; 
	   } 
	   if(pass!=Rpass)
	  {
	     alert("two input of the password have not the same");
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
    <div id="title">Add&nbspNew&nbspAccount</div><br/>
    <form id="fom" action="addinfo" method="POST">
       <table id="ta">
           <tr id="tr1">
               <td id="td">username:&nbsp&nbsp&nbsp</td>
               <td id="td1.2"><input type="text" id="in1" name="0"><span id="1">*</span></td>
           </tr>
            <tr id="tr2">
               <td id="td">password:&nbsp&nbsp&nbsp</td>
               <td id="td2.2"><input type="password" id="in2" name="1"><span id="2">*</span></td>
           </tr>
           <tr id="tr3">
               <td id="td">Repassword:&nbsp&nbsp&nbsp</td>
               <td id="td3.2"><input type="password" id="in3" name="2"><span id="3">*</span></td>
           </tr>
           <tr id="tr4">
               <td id="td">First-name:&nbsp&nbsp&nbsp</td>
               <td id="td4.2"><input type="text" id="in4" name="3"><span id="4">*</span></td>
           </tr>
           <tr id="tr5">
               <td id="td">Last-name:&nbsp&nbsp&nbsp</td>
               <td id="td5.2"><input type="text" id="in5" name="4"></td>
           </tr>
           <tr id="tr6">
               <td id="td">Email:&nbsp&nbsp&nbsp</td>
               <td id="td6.2"><input type="text" id="in6" name="Cemail-first">@<input type="text" name="Cemail-second">
               <span id="6">*</span></td>
           </tr>
           <tr id="tr7">
               <td id="td">phone:&nbsp&nbsp&nbsp</td>
               <td id="td7.2"><input type="text" id="in7" name="5"></td>
           </tr>
            <tr id="tr8">
               <td id="td">region:&nbsp&nbsp&nbsp</td>
               <td id="td8.2"><input type="text" id="in8" name="6"></td>
           </tr>
            <tr id="tr9">
               <td id="td">address:&nbsp&nbsp&nbsp</td>
               <td id="td9.2"><input type="text" id="in9" name="7"></td>
           </tr>
             <tr id="tr10">
               <td id="td">birthday:&nbsp&nbsp&nbsp</td>
               <td id="td10.2"><input type="text" id="in10" name="9"></td>
           </tr>
            <tr id="tr10">
               <td id="td">balance:&nbsp&nbsp&nbsp</td>
               <td id="td10.2"><input type="text" id="in22" name="22"></td>
           </tr>
             <tr id="tr9">
             <td id="td">role:&nbsp&nbsp&nbsp</td>
             <td id="td">
             <input type="radio" id="in10" name="8" checked="checked" value="Customer">customer
             <input type="radio" id="in10" name="8" value="employee">employee
             </td>
           </tr>
         <table>
         <p id="p">
         <input type="reset" value="reset" style="font-size:20px;">
             <input type="button" value="add" style="font-size:20px;" onclick="register()" >
        </form> 
     </center>   
    </p>
    <div id="d">Note:&nbspthe <span id="s">*</span> must be put and pastword at lest 8 length</div>
     <div>
     <form action="EmployeePage.jsp">
     <input type="submit" value="back" style="font-size:20px;">
     </form>
     </div>
    </div>
</body>
</html>