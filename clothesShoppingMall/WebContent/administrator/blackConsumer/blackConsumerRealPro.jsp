<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- �� ������ ���� -->
<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="bcn" class="kr.or.ksmart.dto.BlackConsumer"/>
<jsp:setProperty name="bcn" property="*"/>
<!--
	���� ������ �Ѿ�� name���� BlackConsumerŬ������ setter��� ���ٸ� �ڵ����� ���� �Էµȴ�.
	request.getParameter("treat_reason");�� �ڵ����� �̷������.
-->
<%@ page import="kr.or.ksmart.dao.BlackConsumerDao" %>

<%
	BlackConsumerDao bcdao = new BlackConsumerDao();
	bcdao.blaconTreat(bcn.getBuyer_id(), bcn.getRequest_seller_id(), bcn.getTreat_reason());
	// �⺻Ű�� �����ڿ� �Ǹ��� ���̵� ó�������� �Բ� �Ű������� �Ѱܼ� ���� ���� ����(treat_whether)�� true�� �ٲ۴�.
	response.sendRedirect(request.getContextPath() +
			"/administrator/blackConsumer/blackConsumerList.jsp");
%>