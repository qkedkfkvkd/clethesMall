<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 블랙 컨슈머 제재 -->
<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="bcn" class="kr.or.ksmart.dto.BlackConsumer"/>
<jsp:setProperty name="bcn" property="*"/>
<%@ page import="kr.or.ksmart.dao.BlackConsumerDao" %>

<%
	BlackConsumerDao bcdao = new BlackConsumerDao();
	bcdao.blaconTreat(bcn.getBuyer_id(), bcn.getRequest_seller_id(), bcn.getTreat_reason());
	response.sendRedirect(request.getContextPath() +
			"/administrator/blackConsumer/blackConsumerList.jsp");
%>