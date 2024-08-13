<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@page import="sub1.FileVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	
	//파일번호 수신
	String no = request.getParameter("no");

	FileVO vo = null;

	// 데이터 베이스 조회
	try{
	//1단계
	Context ctx = (Context) new InitialContext().lookup("java:comp/env");
	DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
	
	//2단계
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("select * from `filetest` where `no`=" +no);
	
	if(rs.next()){
		vo = new FileVO(); // 아래에 oname,sname사용될 예정
		vo.setNo(rs.getInt(1));
		vo.setUid(rs.getString(2));
		vo.setName(rs.getString(3));
		vo.setOname(rs.getString(4));
		vo.setSname(rs.getString(5));
		vo.setRdate(rs.getString(6));
				
	}
	rs.close();
	stmt.close();
	conn.close();
	
	
}catch(Exception e){
	e.printStackTrace();
	
}
	
	//response 헤더정보 수정
	response.setContentType("application/octet-stream"); //파일다운로드에 사용
	response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(vo.getOname(), "utf-8")); //응답헤더에 파일 첨부 정보를 설정 
	response.setHeader("Content-Transfer-Encoding", "binary"); //데이터가 바이너리 형식으로 전송
	response.setHeader("Pragma", "no-cache"); //브라우저 캐싱을 방지하기 위해 캐시를 사용하지 않도록 함
	response.setHeader("Cache-Control", "private"); // 응답이 캐시되지 않도록 설정

	//파일 내용 스트림 처리
	String path = application.getRealPath("/uploads"); //해당 폴더 경로를 얻음
	File file = new File(path + File.separator + vo.getSname()); //경로 + / + 파일명 
	
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); //파일을 읽기위한 생성
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); //응답의 출력을 위한 생성
	
	while(true){
		int data = bis.read();
		if(data == -1){
			break; //파일의 데이터를 한 바이트씩 읽어와서 클라이언트로 전송하되 읽은 바이트가 -1이면 파일의 끝을 의미함
		}
		bos.write(data);
	}
	bos.close();
	bis.close();



%>