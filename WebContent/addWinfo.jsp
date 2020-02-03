<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="DatabaseHandle.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>add warehouse</title>
<%
  new LoginServlet().Get(request, response);
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
 #all{
 
 }
 #td{
    color:blue;
    font-size:25px;
    font-weight: bold;
 }
</style>
<script type="text/javascript">
function register(){
	   var name=document.getElementById("in1").value;
	   var region=document.getElementById("in2").value;
	   var address=document.getElementById("in3").value;
	   if(name==""||region==""||address=="")
	   {
	      alert("the * can not be the empty.");
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
    <div id="title">add warehouse</div><br/>
    <form id="fom" action="addWinf" method="POST">
       <table id="ta">
           <tr id="tr1">
               <td id="td">warehouse_name:&nbsp&nbsp&nbsp</td>
               <td id="td1.2"><input type="text" id="in1" name="0"><span id="1">*</span></td>
           </tr>
            <tr id="tr2">
               <td id="td">warehouse_region:&nbsp&nbsp&nbsp</td>
               <td id="td2.2"><input type="text" id="in2" name="1"><span id="2">*</span></td>
           </tr>
           <tr id="tr3">
               <td id="td">warehouse_address:&nbsp&nbsp&nbsp</td>
               <td id="td3.2"><input type="text" id="in3" name="2"><span id="3">*</span></td>
           </tr>
         <table>
         <p id="p">
         <input type="reset" value="reset" style="font-size:30px;">
             <input type="button" value="add" style="font-size:30px;" onclick="register()" >
        </form> 
     </center>   
    </p>
    <div id="d">Note:&nbspthe <span id="s">*</span> must be put </div>
      <div>
     <form action="WareHouse.jsp">
     <input type="submit" value="back" style="font-size:30px;">
     </form>
     </div>
    </div>

</body>
</html>