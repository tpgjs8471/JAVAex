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

<c:set value="${mvo }" var="mvo"></c:set>
<a href="/member/modify?email=${mvo.email }">
	<button type="button">회원정보수정</button>
</a>
<a href="/member/remove?email=${mvo.email }">
	<button type="button">회원 탈퇴</button>
</a>
<table class="table table-hover">
	<tr>
		<th>아이디</th>
		<th>닉네임</th>
		<th>계정 생성일</th>
		<th>마지막 로그인</th>
	</tr>	
	<tr>
		<th>${mvo.email }</th>
		<th>${mvo.nickName }</th>
		<th>${mvo.regAt }</th>
		<th>${mvo.lastLogin }</th>
	</tr>
</table>

<jsp:include page="../common/footer.jsp"></jsp:include>
<script type="text/javascript">
const isOk = `<c:out value="${isOk}" />`;
if (isOk == 1) {
	alert('회원정보 수정 완료');
}
const onlyNick = `<c:out value="${onlyNick}"/>`;
if (onlyNick == 1) {
	alert('닉네임 수정 완료')
}
const 
</script>
</body>
</html>