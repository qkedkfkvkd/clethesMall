<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 블랙컨슈머 제재 하기 -->
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

<h3>블랙컨슈머 제재 처리</h3>
<form action="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerRealPro.jsp"
method="post">
	<input type="hidden" value="<%=bcn.getBuyer_id() %>" name="buyer_id">
	<input type="hidden" value="<%=bcn.getRequest_seller_id() %>" name="request_seller_id">
	<!-- 블랙컨슈머 테이블의 기본키가 되는 블랙컨슈머 구매자 아이디와 요청 판매자 아이디를 숨겨서 다음 폼에 넘긴다. -->
	<table>
		<tr>
			<td>
				<h5>블랙 컨슈머 처리</h5>
			</td>
		</tr>
		<tr>
			<td>
				<textarea rows="2" cols="16">
				정말 <%=bcn.getBuyer_id() %>님을 블랙컨슈머로 <br/>지정 처리하시겠습니까?
				</textarea>
			</td>
		</tr>
		<tr>
			<td>
				처리 사유 작성 <br/>
				<textarea rows="2" cols="16" name="treat_reason"></textarea>
			</td>
		</tr>
		<tr>
			<td>
				<button type="submit">처리하기</button>
			</td>
		</tr>
	</table>
</form>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>