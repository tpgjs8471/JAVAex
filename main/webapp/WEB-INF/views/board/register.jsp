<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/nav.jsp"></jsp:include>
<sec:authentication property="principal.mvo.email" var="authEmail"/>
<form action="/board/register" method="post" enctype="multipart/form-data">
	<div class="mb-3">
	  <label for="exampleFormControlInput1" class="form-label">title</label>
	  <input type="text" class="form-control" name="title" id="t" placeholder="title" required="required">
	</div>
	<div class="mb-3">
	  <label for="exampleFormControlInput1" class="form-label"></label>
	  <input type="text" class="form-control" name="writer" id="w" value="${authEmail}" readonly="readonly">
	</div>
	<div class="mb-3">
	  <label for="exampleFormControlTextarea1" class="form-label">content</label>
	  <textarea class="form-control" name="content" id="c" rows="3"></textarea>
	</div>
	<div class="mb-3">
	  <input type="file" class="form-control" name="files" id="files" style="display: none;" multiple="multiple">
	  <!-- input button trigger 용도의 botton -->
	  <button type="button" id="trigger" class="btn btn-outline-primary">File Upload</button>
	</div>
	
	<div class="mb-3" id="fileZone">
	</div>
	<button type="submit" class="btn btn-dark" id="regBtn">등록</button>
</form>

<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html> 