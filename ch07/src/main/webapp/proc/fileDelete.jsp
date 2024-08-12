<%@page import="sub1.FileVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.io.File"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	
	String no = request.getParameter("no");
	String sname = request.getParameter("sname");
	//FileVO vo =null;
	try{
	//1단계
	Context ctx = (Context) new InitialContext().lookup("java:comp/env");
	DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
	
	//2단계
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	/*ResultSet rs = stmt.executeQuery("select * from `filetest` where `no`=" +no);
	
	if(rs.next()){
		vo = new FileVO();
		vo.setNo(rs.getInt(1));
		vo.setUid(rs.getString(2));
		vo.setName(rs.getString(3));
		vo.setOname(rs.getString(4));
		vo.setSname(rs.getString(5));
		vo.setRdate(rs.getString(6));
				
	}*/
	stmt.executeUpdate("delete from `filetest` where `no`="+no);
	conn.close();
	stmt.close();
	//rs.close();
	
	}catch(Exception e){
		e.printStackTrace();
	
	}
	String path = application.getRealPath("/uploads");
	File file = new File(path + File.separator + sname); //vo.getSname());
	file.delete();
	

	response.sendRedirect("/ch07/2.fileDownload.jsp");
%>
