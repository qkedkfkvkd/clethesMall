<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>

<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String dbid = null;
	String dbname = null;
	String dblevel = null;
	String alert = null;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://localhost:3306/dev32db?" +
				"useUnicode=true&characterEncoding=euckr";
		String dbUser = "dev32id";
		String dbPass = "dev32pw";
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		System.out.println(conn + "<-- conn   login_pro.jsp");
		
		String sql = "SELECT u_pw, u_name, u_level FROM member WHERE u_id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		System.out.println(pstmt + "<-- pstmt   login_pro.jsp");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   login_pro.jsp");
		
		if(rs.next()) {
			
			if((rs.getString("u_pw")).equals(pw)) {
				System.out.println("로그인 성공");
				
				dbid = id;
				dbname = rs.getString("u_name");
				dblevel = rs.getString("u_level");
				
				System.out.println(dbid + "   dbid");
				System.out.println(dbname + "   dbname");
				System.out.println(dblevel + "   dblevel");
				
				session.setAttribute("sId", dbid);
				session.setAttribute("sName", dbname);
				session.setAttribute("sLevel", dblevel);
			} else {
				alert = "비밀번호를 잘못 입력하셨습니다.";
			}
		} else {
			alert = "아이디를 잘못 입력하셨습니다.";
		}
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	} catch(SQLException ex) {
		out.println(ex.getMessage());
		ex.printStackTrace();
	} finally {
		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
%>

