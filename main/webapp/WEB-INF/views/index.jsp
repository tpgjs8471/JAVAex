<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<jsp:include page="common/header.jsp" />
<jsp:include page="common/nav.jsp" />
<h1>
	Hello world!  
</h1>
<h3>dge positioning 유틸리티를 사용하여 요소를 쉽게 정렬합니다. 형식은 {property}-{position} 입니다.</h3>
<jsp:include page="common/footer.jsp" />
<script type="text/javascript">
	const isOk = `<c:out value="${isOk}"/>`;
	if (isOk == 1) {
		alert('회원 탈퇴 완료');
	}
</script>
</body>
</html>
