<%@page import="org.jdom2.output.Format"%>
<%@page import="org.jdom2.output.XMLOutputter"%>
<%@page import="org.jdom2.Element"%>
<%@page import="org.jdom2.Document"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="user1.User1VO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%


	List <User1VO> users = new ArrayList<>();
	
	try{
		//데이터베이스 처리

	//1단계 - JNDI 서비스 객체 생성
	Context ctx = (Context) new InitialContext().lookup("java:comp/env");
	
	//2단계 - 커넥션 가져오기
	DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
	Connection conn = ds.getConnection();
	//3단계 - SQL 실행 객체 생성
	Statement stmt = conn.createStatement();
	//4단계 - SQL 실행
	String sql = "select * from `tb1user`";
	ResultSet rs = stmt.executeQuery(sql);
	//5단계 - 결과처리
	while(rs.next()){
		User1VO vo = new User1VO();
		vo.setUid(rs.getString(1));
		vo.setName(rs.getString(2));
		vo.setHp(rs.getString(3));
		vo.setAge(rs.getString(4));
		vo.setAdr(rs.getString(5));
		
		users.add(vo);
	}
	//6단계 - 데이터베이스 종료
	}catch(Exception e){
		e.printStackTrace();
		
	}
	//System.out.println(users);
	//XML 생성 빠르게 하지 마세요....
	Document doc = new Document();
	Element eUsers = new Element("Users");
	
	for(User1VO vo : users){
		Element eUser = new Element("user");
		Element eUid = new Element("uid");
		Element eName = new Element("name");
		Element eAge = new Element("age");
		Element eHp = new Element("hp");
		Element eAdr = new Element("adr");
		
		eUid.setText(vo.getUid());
		eName.setText(vo.getName());
		eAge.setText(vo.getAge());
		eHp.setText(vo.getHp());
		eAdr.setText(vo.getAdr());
		
		eUser.addContent(eUid);
		eUser.addContent(eName);
		eUser.addContent(eAge);
		eUser.addContent(eHp);
		eUser.addContent(eAdr);
		
		eUsers.addContent(eUser);
		doc.setRootElement(eUsers);
	}
	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
	String xml = outputter.outputString(doc);
	out.print(xml);
	
	%>
