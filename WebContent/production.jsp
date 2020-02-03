<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="DatabaseHandle.*" import="java.util.List"
    import="java.util.ArrayList" import="java.util.Set" import="java.util.HashSet" import="java.util.Iterator"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Customer production</title>

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
 #all{
 
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
	   var s=document.getElementById("in5").value;
	   var email=document.getElementById("in6").value;
	   var phone=document.getElementById("in7").value;
	   var t=document.getElementById("in8").value;
	   var l=document.getElementById("in9").value;
	   var q=document.getElementById("in10").value;
	   if(user==""||pass==""||Rpass==""||Cn==""||email==""||s==""||t==""||l==""||phone==""||q=="")
	   {
	      alert("all can not be the empty.");
	      return; 
	   } 
	   if(isNaN(Rpass))
	   {
		  alert("size to min_quantity must enter the number");
		  return;
	   }
	   if(isNaN(Cn))
	   {
		  alert("size to min_quantity must enter the number");
		  return;
	   }
	   if(isNaN(s))
	   {
		  alert("size to min_quantity must enter the number");
		  return;
	   }
	   if(isNaN(email))
	   {
		  alert("size to min_quantity must enter the number");
		  return;
	   }
	   if(isNaN(phone))
	   {
		  alert("size to min_quantity must enter the number");
		  return;
	   }
	   if(parseInt(phone)>parseInt(email))
	   {
		 alert ("min_quantity can not large the quantity");
		 return;
	   }
	
	  else
	  {
		  document.getElementById("fom").submit();
	  }
	   
     }
</script>
</head>
<body><center>
<%  new LoginServlet().Get(request, response);
 /* if(judge.equals("true"))
  {
	  return;
  } */

  
  
 %>
    <div id="all">
    <div id="title">upload product</div><br/>
    <form id="fom" action="product" method="GET">
       <table id="ta">
           <tr id="tr1">
               <td id="td">product_name:&nbsp&nbsp&nbsp</td>
               <td id="td1.2"><input type="text" id="in1" name="0">
           </tr>
            <tr id="tr2">
               <td id="td">category:&nbsp&nbsp&nbsp</td>
               <td id="td2.2"><input type="text" id="in2" name="1">
           </tr>
           <tr id="tr3">
               <td id="td">size:&nbsp&nbsp&nbsp</td>
               <td id="td3.2"><input type="text" id="in3" name="2">
           </tr>
           <tr id="tr4">
               <td id="td">weight:&nbsp&nbsp&nbsp</td>
               <td id="td4.2"><input type="text" id="in4" name="3">
           </tr>
           <tr id="tr5">
               <td id="td">price:&nbsp&nbsp&nbsp</td>
               <td id="td5.2"><input type="text" id="in5" name="4"></td>
           </tr>
           <tr id="tr6">
               <td id="td">quantity:&nbsp&nbsp&nbsp</td>
               <td id="td6.2"><input type="text" id="in6" name="5">
           </tr>
           <tr id="tr7">
               <td id="td">min_quantity:&nbsp&nbsp&nbsp</td>
               <td id="td7.2"><input type="text" id="in7" name="6"></td>
           </tr>
            <tr id="tr8">
               <td id="td">country_of_origian:&nbsp&nbsp&nbsp</td>
               <td id="td8.2"><input type="text" id="in8" name="7"></td>
           </tr>
            <tr id="tr9">
               <td id="td">region:&nbsp&nbsp&nbsp</td>
               <td id="td9.2"><input type="text" id="in9" name="8"></td>
               </tr> 
             <tr id="tr10">
               <td id="td">description:&nbsp&nbsp&nbsp</td>
               <td id="td10.2"><input type="text" id="in10" name="9"></td>
               </tr> 
              <tr it="tr12">
             <td id="td">  update your: your image image_url is: </td>
             <td id="12.2"><select id="i" name="10">
              <%
                ArrayList<String>list =new ArrayList<String>();
                list=new uploadPhoto().geturl();
                for(int i=0;i<list.size();i++){
              %>
            <option><%=list.get(i)%><%}%></option>
            </select></td> 
              </tr>                          
         <table>
         <p id="p">
         <input type="reset" value="reset" style="font-size:20px;">
             <input type="button" value="upload" style="font-size:20px;" onclick="register()" >
        </form> </br></br>
        
     </center>   
    </p>
    <div id="d">all must be put </div>
   
    </div>
    <div>
     <form action="CustomerPage.jsp">
     <input type="submit" value="back" style="font-size:30px;">
     </form>
     </div>

</body>
</html>