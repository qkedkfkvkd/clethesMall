<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String sId = (String)session.getAttribute("sId");
	String sName = (String)session.getAttribute("sName");
	String sLevel = (String)session.getAttribute("sLevel");
	
	System.out.println(sId+"<- sId   adminTop.jsp");
	System.out.println(sName+"<- sName   adminTop.jsp");
	System.out.println(sLevel+"<- sLevel   adminTop.jsp");
%>
	<!-- Begin Wrapper -->
	<div id="wrapper">
	
		<!-- Begin Header -->
		<div id="header">
			<a href="<%=request.getContextPath()%>/administrator/member/allMem.jsp">01 ��ü ȸ�� ����</a>
			
			<a href="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerList.jsp">02 �������� ����</a>
			<a href="<%=request.getContextPath()%>/administrator/notGoodSeller/notGoodSellerList.jsp">03 �����Ǹ��� ����</a>
			
			<a href="<%=request.getContextPath()%>/administrator/product/productList.jsp">04 ��ǰ ����</a>
			<a href="<%=request.getContextPath()%>/administrator/product/productSearchForm.jsp">05 ��ǰ �˻�</a>
			
			<a href="<%=request.getContextPath()%>/administrator/productOption/productOpjoinList.jsp">06 ��ǰ �ɼ� ����</a>
			<a href="<%=request.getContextPath()%>/administrator/productOption/productOpjoinSearchForm.jsp">07 ��ǰ �ɼ� �˻�</a>
			
			<a href="<%=request.getContextPath()%>/administrator/proOrder/proOrderList.jsp">08 �ֹ� ����</a>
			
			<a href="<%=request.getContextPath()%>/administrator/returnRefund/returnRefundList.jsp">09 ��ǰ/ȯ�� ����</a>
			<br/><br/>
			<%= sName %> �� <%= sLevel %> ���� �α��� ��
			<a href="<%= request.getContextPath() %>/login/logout.jsp">�α׾ƿ�</a>
		</div>
		<!-- End Header -->
		
		
		
		
		
		