<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/nav.jsp"></jsp:include>


	<form action="/member/modify" method="post">
		<table class="table table-hover">
			<tr>
			<th>Email</th>
			<td><input type="text" name="email" value="${mvo.email }" readonly="readonly"></td>
			</tr>
			<tr>
			<th>Nick_Name</th>
			<td><input type="text" name="nickName" value="${mvo.nickName }"></td>
			</tr>
			<tr>
			<th>PWD</th>
			<td><input type="text" name="pwd"></td>
			</tr>	
		</table>
		
		<button type="submit" class="btn btn-success" id="regBtn">수정</button>
	</form>
	
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>