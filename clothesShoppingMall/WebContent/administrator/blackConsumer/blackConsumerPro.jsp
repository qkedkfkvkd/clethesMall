<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- �������� ���� �ϱ� -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<jsp:useBean id="bcn" class="kr.or.ksmart.dto.BlackConsumer"/>
<jsp:setProperty name="bcn" property="*"/>

<h3>�������� ���� ó��</h3>
<form action="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerRealPro.jsp"
method="post">
	<input type="hidden" value="<%=bcn.getBuyer_id() %>" name="buyer_id">
	<input type="hidden" value="<%=bcn.getRequest_seller_id() %>" name="request_seller_id">
	<!-- �������� ���̺��� �⺻Ű�� �Ǵ� �������� ������ ���̵�� ��û �Ǹ��� ���̵� ���ܼ� ���� ���� �ѱ��. -->
	<table>
		<tr>
			<td>
				<h5>�� ������ ó��</h5>
			</td>
		</tr>
		<tr>
			<td>
				<textarea rows="2" cols="16">
				���� <%=bcn.getBuyer_id() %>���� �������ӷ� <br/>���� ó���Ͻðڽ��ϱ�?
				</textarea>
			</td>
		</tr>
		<tr>
			<td>
				ó�� ���� �ۼ� <br/>
				<textarea rows="2" cols="16" name="treat_reason"></textarea>
			</td>
		</tr>
		<tr>
			<td>
				<button type="submit">ó���ϱ�</button>
			</td>
		</tr>
	</table>
</form>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>