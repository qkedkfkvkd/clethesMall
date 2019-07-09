<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 블랙 컨슈머 제재 -->
<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="bcn" class="kr.or.ksmart.dto.BlackConsumer"/>
<jsp:setProperty name="bcn" property="*"/>
<!--
	이전 폼에서 넘어온 name값과 BlackConsumer클래스의 setter명과 같다면 자동으로 값이 입력된다.
	request.getParameter("treat_reason");가 자동으로 이루어진다.
-->
<%@ page import="kr.or.ksmart.dao.BlackConsumerDao" %>

<%
	BlackConsumerDao bcdao = new BlackConsumerDao();
	bcdao.blaconTreat(bcn.getBuyer_id(), bcn.getRequest_seller_id(), bcn.getTreat_reason());
	// 기본키인 구매자와 판매자 아이디를 처리사유와 함께 매개변수로 넘겨서 현재 제재 여부(treat_whether)를 true로 바꾼다.
	response.sendRedirect(request.getContextPath() +
			"/administrator/blackConsumer/blackConsumerList.jsp");
%>