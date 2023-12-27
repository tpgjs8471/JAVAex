<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.outer{
width: 500px;
top: 50%;
left: 50%;
}
</style>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/nav.jsp"></jsp:include>
<div class="outer">
	<form action="/member/login" method="post">
		<div class="mb-3">
	  <label for="e" class="form-label">EMAIL</label>
	  <input type="email" class="form-control" name="email" id="e" placeholder="example@OOO.com">
	</div>
	 <div class="row g-3 align-items-center">
  <div class="col-auto">
    <label for="inputPassword6" class="col-form-label">Password</label>
  </div>
  <div class="col-auto">
    <input type="password" id="p" class="form-control" name="pwd" aria-describedby="passwordHelpInline">
  </div>
</div>
	 ${param.errMsg }
	 <c:if test="${not empty param.errMsg }">
	 	<div class="text-danger mb-3">
	 		<c:choose>
	 			<c:when test="${param.errMsg eq 'Bad credentials' }">
	 				<c:set var="errText" value="이메일 & 비밀번호가 일치하지 않습니다."></c:set>
	 			</c:when>
	 			<c:otherwise>
	 				<c:set var="errText" value="관리자에게 문의해주세요"></c:set>
	 			</c:otherwise>
	 		</c:choose>
	 		${errText }
	 	</div>
	 </c:if>
	 <button type="submit" class="btn btn-dark">로그인</button>
	</form>
</div>
<jsp:include page="../common/footer.jsp" />
</body>
</html>