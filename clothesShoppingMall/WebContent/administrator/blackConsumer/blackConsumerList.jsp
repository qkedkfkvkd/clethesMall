<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 전체 제재 요청되거나 제재된 블랙컨슈머 리스트 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.BlackConsumerDao" %>
<%@ page import="kr.or.ksmart.dto.BlackConsumer" %>
<%@ page import="java.util.List" %>

<h3>블랙컨슈머 전체 리스트</h3>
<table border="1">
	<tr>
		<th>재제된 구매자</th>
		<th>요청 판매자</th>
		<th>제재 여부</th>
		<th>상세보기</th>
	</tr>

<%
	BlackConsumerDao bcdao = new BlackConsumerDao();
	List<BlackConsumer> bclist = bcdao.blackAllList();
	// 현재 재제 중(treat_whether : 1 (true))거나 제재 요청 중(treat_whether:0 (false))인
	// 블랙컨슈머 전체 리스트
	
	List<BlackConsumer> bcReqlist = bcdao.request_blacon();
	// 판매자로부터 제재 요청된 (treat_whether:0 (false)) 블랙컨슈머 리스트 불러오기
	
	for(int i=0; i<bclist.size(); i++) { // 전체 리스트 만큼 돌린다.
%>
	<tr>
		<td><%=bclist.get(i).getBuyer_id() %></td>
		<td><%=bclist.get(i).getRequest_seller_id() %></td>
		<td><%=bclist.get(i).isTreat_whether() %></td>
		<td>
<a href="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerDetail.jsp?buy_id=<%=bclist.get(i).getBuyer_id()%>&re_sel_id=<%=bclist.get(i).getRequest_seller_id()%>">
상세 보기</a>										<!-- 블랙컨슈머 상세 조회를 위해 기본키가 되는 구매자 아이디와 판매자 아이디를 넘긴다. -->
		</td>
	</tr>
<%	}%>
</table>
<hr/>
<table border="1">
<%	if(bcReqlist.size() == 0) {%> <!-- 제재 여부가 (treat_whether:1 (true))인 블랙컨슈머들만 있다면 -->
	<tr>
		<td>신청된 블랙컨슈머 리스트가 없습니다.</td>
	</tr>
<%
	} else {
		for(int i=0; i<bcReqlist.size(); i++) { // treat_whether:0인 블랙컨슈머가 있다면
%>
	<tr>
		<td>
			판매자<%=bcReqlist.get(i).getRequest_seller_id()%>님께서
			구매자<%=bcReqlist.get(i).getBuyer_id()%>를 블랙컨슈머 지정 요청
<a href="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerForm.jsp?buy_id=<%=bcReqlist.get(i).getBuyer_id()%>&re_sel_id=<%=bcReqlist.get(i).getRequest_seller_id()%>">
내용보기</a>										<!-- 블랙컨슈머 상세 조회를 위해 기본키가 되는 구매자 아이디와 판매자 아이디를 넘긴다. -->
		</td>
	</tr>
<%
		}
	}
%>
</table>
<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>