<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="DatabaseHandle.*" import="java.util.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
 input{
    border-top:none;
    border-left:none;
    border-right:none;
    border-bottom-color:green;
    background-color: yellow;
}
#td{
    color:blue;
    font-size:25px;
    font-weight: bold;
 }
</style>
<script type="text/javascript">
           function login(){
        	   var name=document.getElementById("nameid").value;
        	   var psd=document.getElementById("pwid").value;
        	   var Rpsd=document.getElementById("Rpwid").value;
                   if(psd==""||name==""){
                           alert("please enter the password and name");
                           return;
                         }
                   if(psd!=Rpsd){
                	   alert("two passwords have not the same");
                	   return;
                   }
                   else {
                       document.getElementById("d").submit();
                        }
                 
                }
 </script>
<title>website</title>
</head>
<body>
<font  size="5">
          <b>
             <center>welcome to our shopping
          </b>
          </font><br/><br/>
          <form id="d" action="Login" method="POST">
          <table border="0" style="font-size:20px;">
              <tr>   
                    <td id="td">username:</td>       
                    <td><input type="text"  id="nameid" name="user" size="30"> </td>
              </tr>
              <tr>
                    <td id="td">password:</td>
                    <td><input type="password"  id="pwid" name="pass" size="30" ></td>
              </tr>
                    <tr>
                    <td id="td">ReEnterPassword:</td>
                    <td><input type="password"  id="Rpwid" name="Rpass" size="30" ></td>
              </tr>
             <tr>
                 <td id="td">role:&nbsp&nbsp&nbsp</td>
                 <td id="td">
                 <input type="radio" id="in10" name="8" checked="checked" value="Customer">customer
                 <input type="radio" id="in10" name="8" value="employee">employee
                 </td>
           </tr>
             </center>
          </table>
          <p><input type="reset" value="reset" style="font-size:20px;">
             <input type="button" value="login" style="font-size:20px;" onclick="login()" >
         </form>
         </p>
          <font size="5" color="red">you username or password or role selected are not correct,please retry or <a href="Register.jsp"> register </a>now</font>

</body>
</html>