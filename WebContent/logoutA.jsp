<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>error</title>
</head>
<body>
<style type="text/css">
#second{
    color:red;
}
</style>
<%
    HttpSession session1=request.getSession();
    session1.setAttribute("user", "false");
    System.out.println(session1.getAttribute("user"));
%>
<span > you delete your information so please in <span id="second" >10</span> secends will be to the register page to
  Re-register again </span> 

   
  
  <script type="text/javascript">    
    var num = document.getElementById("second").innerHTML;  
  
    
   function count()  
    {  
        num--;  
        document.getElementById("second").innerHTML=num;  
        if(num==0)  
        {  
            location.assign("Register.jsp");  
       }  
   }  
    setInterval("count()",1000);  
 
    function back()  
    {  
       window.history.back();  
  }  
     
 </script>   

</body>
</html>