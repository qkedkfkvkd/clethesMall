<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 개인 회원 내용 조회 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.MemberDao" %>
<%@ page import="kr.or.ksmart.dto.Member" %>


<%
	String u_id = request.getParameter("send_id");
	System.out.println(u_id + "<-- u_id   gaeinMem.jsp");
	MemberDao mdao = new MemberDao();
	Member mem = mdao.gaeinMem(u_id);
	// 회원 테이블의 기본키인 아이디로 회원 상세정보 반환
%>

<h3>개인 회원 상세 보기</h3>
<table border="1">
	<tr>
		<th>아이디</th>
		<td><%=mem.getU_id()%></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><%=mem.getU_name()%></td>
	</tr>
	<tr>
		<th>권한</th>
		<td><%=mem.getU_level()%></td>
	</tr>
	<tr>
		<th>성별</th>
		<td><%=mem.getU_gender()%></td>
	</tr>
	<tr>
		<th>폰번호</th>
		<td><%=mem.getU_phone()%></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><%=mem.getU_email()%></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><%=mem.getU_address()%></td>
	</tr>
	<tr>
		<th>제재 여부</th>
		<td><%=mem.getU_address()%></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="<%=request.getContextPath()%>/administrator/member/allMem.jsp">리스트로</a>
		</td>
	</tr>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>


