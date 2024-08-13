<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String jsonData = "{\"uid\" : \"a101\", \"name\" : \"김춘추\", \"age\" :  24, \"addr\" : \"부산\"}";
	
	//JSON 출력
	out.print(jsonData);
%>

