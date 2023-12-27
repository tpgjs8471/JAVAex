<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<c:set value="${bdto.bvo }" var="bvo"></c:set>

<form action="/board/modify" method="post" enctype="multipart/form-data">
	
		<table class="table table-hover">
			<tr>
		<th>BNO</th>
		<td><input type="text" name="bno" value="${bvo.bno }" readonly="readonly"></td>
	</tr>
	<tr>
		<th>TITLE</th>
		<td><input type="text" name="title" value="${bvo.title }"></td>
	</tr>
	<tr>
		<th>WRITER</th>
		<td><input type="text" name="writer" value="${bvo.writer }" readonly="readonly"></td>
	</tr>
	<tr>
		<th>CONTENT</th>
		<td><textarea rows="5" name="content" cols="30" ${bvo.content }></textarea></td>
	</tr>
	<tr>
		<th>REGISTERDATE</th>
		<td><input type="text" name="registerdate" value="${bvo.regAt }" readonly="readonly"></td>
	</tr>
	</table>
	<c:set value="${bdto.flist }" var="flist"></c:set>
	<div>
		<ul>
			<c:forEach items="${flist }" var="fvo">
				<li>
					<c:choose>
						<c:when test="${fvo.fileType > 0 }">
							<div>
								<img alt="그림없음" src="/upload/${fn:replace(fvo.saveDir, '\\','/') }/${fvo.uuid}_th_${fvo.fileName}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
							
							</div>
						</c:otherwise>
					</c:choose>
					<div>
						<div class="ms-2 me-auto">${fvo.fileName }</div>
							${fvo.regAt }
							<span>${fvo.fileSize }</span>
							<button type="button" class="file-x btn btn-danger" data-uuid="${fvo.uuid}">삭제</button>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="mb-3">
	<input type="file" class="form-control" name="files" id="files" style="display: none;" multiple="multiple">
	  <!-- input button trigger 용도의 botton -->
	  <button type="button" id="trigger" class="btn btn-outline-primary">File Upload</button>
	</div>
	
	<div class="mb-3" id="fileZone">
	</div>
	<a href="/board/list">
	<button type="submit" class="btn btn-success" id="regBtn">수정</button>
	</a>
	
	</form>
	
	<a href="/board/list">
	<button type="button" class="btn btn-success"> 리스트 </button>
	</a>
<jsp:include page="../common/footer.jsp" />

<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<script type="text/javascript" src="/resources/js/boardModify.js"></script>
</body>
</html>