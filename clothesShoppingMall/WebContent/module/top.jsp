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
		 
/module/top.jsp		       ��� �޴�	<br><br>
<%
if(sLevel != null){
	if(sLevel.equals("������")){
%>
<a href="<%= request.getContextPath() %>/minsert/m_insert_form.jsp">01 ȸ�����</a>
<%		
	}else if(sLevel.equals("�Ǹ���")){
%>
<a href="<%= request.getContextPath() %>/minsert/m_insert_form.jsp">01 ȸ�����</a>
<%		
	}else if(sLevel.equals("������")){
		response.sendRedirect(request.getContextPath() + "/administrator/adminIndex.jsp");		
	}
%>
	<br/><br/>
	<%= sName %> �� <%= sLevel %> ���� �α��� ��
	<a href="<%= request.getContextPath() %>/login/logout.jsp">�α׾ƿ�</a>
<%	
}else{
%>
<a href="<%= request.getContextPath() %>/minsert/m_insert_form.jsp">01 ȸ�����</a>
<!-- �α��� �� ȭ�� ���� -->
<br/><br/>
<form action="<%= request.getContextPath() %>/login/loginPro.jsp" method="post">
	���̵� : <input type="text" name="id">
	�� ��   : <input type="password" name="pw">
	<input type="submit" value="�α���">
</form>
<!-- �α��� �� ȭ�� End -->	 
<%
}
%>
	 
	 
	       		 
			   
		 </div>
		 <!-- End Header -->
		 
		 
		 
		 
		 
		 
		 
		 
		 