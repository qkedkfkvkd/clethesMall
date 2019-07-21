<%@ page language="java" contentType="application/json; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%@ page import="kr.or.ksmart.dao.MemberDao" %>
<%
	String u_id = request.getParameter("userId");
	System.out.println(u_id + " <- u_id   memberIdChk.jsp");
	
	MemberDao mdao = new MemberDao();
	int cnt = mdao.memIdChk(u_id);
	out.print(cnt);
%>