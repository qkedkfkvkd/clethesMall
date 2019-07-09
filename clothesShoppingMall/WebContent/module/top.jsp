<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String sId = (String)session.getAttribute("sId");
String sName = (String)session.getAttribute("sName");
String sLevel = (String)session.getAttribute("sLevel");
System.out.println(sName+"<- S_NAME top.jsp");
System.out.println(sLevel+"<- sLevel top.jsp");
%>
   <!-- Begin Wrapper -->
   <div id="wrapper">
   
         <!-- Begin Header -->
         <div id="header">
		 
/module/top.jsp		       상단 메뉴	<br><br>
<%
if(sLevel != null){
	if(sLevel.equals("구매자")){
%>
<a href="<%= request.getContextPath() %>/minsert/m_insert_form.jsp">01 회원등록</a>
<%		
	}else if(sLevel.equals("판매자")){
%>
<a href="<%= request.getContextPath() %>/minsert/m_insert_form.jsp">01 회원등록</a>
<%		
	}else if(sLevel.equals("관리자")){
		response.sendRedirect(request.getContextPath() + "/administrator/adminIndex.jsp");		
	}
%>
	<br/><br/>
	<%= sName %> 님 <%= sLevel %> 권한 로그인 중
	<a href="<%= request.getContextPath() %>/login/logout.jsp">로그아웃</a>
<%	
}else{
%>
<a href="<%= request.getContextPath() %>/minsert/m_insert_form.jsp">01 회원등록</a>
<!-- 로그인 전 화면 시작 -->
<br/><br/>
<form action="<%= request.getContextPath() %>/login/loginPro.jsp" method="post">
	아이디 : <input type="text" name="id">
	비 번   : <input type="password" name="pw">
	<input type="submit" value="로그인">
</form>
<!-- 로그인 전 화면 End -->	 
<%
}
%>
	 
	 
	       		 
			   
		 </div>
		 <!-- End Header -->
		 
		 
		 
		 
		 
		 
		 
		 
		 