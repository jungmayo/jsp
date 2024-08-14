<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="user2.User2VO"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	

	//JSON 문자열 스트림 처리
	BufferedReader reader = request.getReader();
	StringBuilder requestBody = new StringBuilder();
	
	String line;
	while((line = reader.readLine()) != null){
		requestBody.append(line);
	}
	reader.close();
	
	//JSON 파싱
	Gson gson = new Gson();
	User2VO user2 = gson.fromJson(requestBody.toString(), User2VO.class);
	System.out.println(user2);
	

	/*String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String age = request.getParameter("age");
	String adr = request.getParameter("adr");*/
	
	
	int rowCount = 0;
	try {
		//데이터베이스 처리

		//1단계 - JNDI 서비스 객체 생성
		Context ctx = (Context) new InitialContext().lookup("java:comp/env");
		
		//2단계 - 커넥션 가져오기
		DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
		Connection conn = ds.getConnection();
		String sql = "insert into `user2` values (?,?,?,?)";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, user2.getUid());
		psmt.setString(2, user2.getName());
		psmt.setString(3, user2.getBirth());
		psmt.setString(4, user2.getAdrr());
		//3단계 - SQL실행
		rowCount = psmt.executeUpdate();
		//4단계 - 결과처리(select일 경우)
		//5단계 - 데이터베이스 종료
		psmt.close();
		conn.close();
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	//목록이동
	//response.sendRedirect("/ch06/user1/list.jsp");
	//String jsonData = "{\"result1\": "+rowCount+"}";
	
	//JSON 출력
	JsonObject json = new JsonObject();
	json.addProperty("result", rowCount);
	out.print(json.toString());

%>