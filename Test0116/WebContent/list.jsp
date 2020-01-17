<%@page import="com.naver.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%
	Object obj = session.getAttribute("login");
	MemberDTO login = null;
	
	if(obj != null){
		login = (MemberDTO)obj;
		
		out.print(login.getName());
		out.print(" 님, 환영합니다."); 
		out.print("<a href = 'logout.do'>로그아웃</a>");
	}else{
		out.print("<a href = 'loginui.do'>로그인</a>");
	}

%>


<h1>회원 목록</h1>
<%
	List<MemberDTO> list = (List<MemberDTO>)request.getAttribute("list");
	for(int i = 0; i < list.size(); i++){
		MemberDTO dto = list.get(i);
		out.print(dto.getId());
		out.print(":");
		out.print("<a href= 'read.do?id=${id}'>");//"+dto.getId()+"
		out.print(dto.getName());
		out.print("</a>");
		/* out.print(dto.getAge()); */
		out.print("<br>");
	}
%>
</body>
</html>