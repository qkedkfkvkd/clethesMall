<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%@ page import="kr.or.ksmart.dao.MemberDao"%>

<% request.setCharacterEncoding("euc-kr");%>
<jsp:useBean id="mem" class="kr.or.ksmart.dto.Member"/>
<jsp:setProperty name="mem" property="*"/>

<%
	MemberDao mdao = new MemberDao();
	//mdao.memberInsert(mem);
	response.sendRedirect(request.getContextPath() + "/index.jsp");
%>