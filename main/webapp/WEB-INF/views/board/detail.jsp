<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/nav.jsp"></jsp:include>
<c:set value="${bdto.bvo }" var="bvo"></c:set>
<table class="table table-hover">
		<tr>
		<th>BNO</th>
		<td>${bvo.bno }</td>
	</tr>
	<tr>
		<th>TITLE</th>
		<td>${bvo.title }</td>
	</tr>
	<tr>
		<th>WRITER</th>
		<td>${bvo.writer }</td>
	</tr>
	<tr>
		<th>CONTENT</th>
		<td>${bvo.content }</td>
	</tr>
	<tr>
		<th>REGISTERDATE</th>
		<td>${bvo.regAt }</td>
	</tr>
	<tr>
		<th>READCOUNT</th>
		<td>${bvo.readCount }</td>
	</tr>
	<tr>
		<th>modAt</th>
		<td>${bvo.modAt }</td>
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
								<img alt="그림없음" src="/upload/${fn:replace(fvo.saveDir,'\\','/') }/${fvo.uuid}_th_${fvo.fileName}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
							
							</div>
						</c:otherwise>
					</c:choose>
					<div>
						<div>${fvo.fileName }</div>
						${fvo.regAt }
						<span>${fvo.fileSize }</span>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<c:if test="${authEmail eq bvo.writer }">
	<a href="/board/modify?bno=${bvo.bno }">
	<button type="button" class="btn btn-success">수정</button>
	</a>
	<a href="/board/remove?bno=${bvo.bno }">
	<button type="button" class="btn btn-danger">삭제</button>
	</a>
	</c:if>
	</sec:authorize>
	<br><br>
	<a href="/board/list">
	<button type="button" class="btn btn-primary">리스트</button>
	</a><br><br><br>
	
		<!-- 댓글 -->
<%-- 	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
</sec:authorize> --%>
	<div id="rp">
	<div class="mb-3">
	
  <label for="exampleFormControlInput1" class="form-label">Email</label>
  <input type="email" class="form-control" id="exampleFormControlInput1" value="${authEmail} "  readonly="readonly">
</div>
<div class="mb-3">
  <label for="exampleFormControlTextarea1" class="form-label">댓글쓰기 </label>
  <textarea class="form-control" id="text" rows="3"></textarea>
  <button type="button" class="btn btn-success" id="cmtPostBtn">등록</button>
</div>
</div>
	
	<div>

		<ul class="list-group list-group-flush" id="cmtListArea">
		  <li class="list-group-item">
		  	<div class="mb-3">
		  		<div  class="fw-bold" >writer</div>
		  		Content
		  	</div>
		  	<span class="badge rounded-pill text-bg-dark">modAt</span>
		  </li>
		</ul>
		
		<div>
			<div>
				<button type="button" id="moreBtn" data-page="1" class="btn btn-warning" style="visibility: hidden">MORE+</button>
			</div>
		</div>

		<!-- 모달창 -->
		<div class="modal" id="myModal" tabindex="-1">
		  <div class="modal-dialog">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title">${authEmail }</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
		       <div class="input-group mb-3">
		       <input type="text" class="form-control" placeholder="Test Content" id="cmtTextMod">
		  <button type="button" class="btn btn-success" id="cmtModBtn">등록</button>
		       </div>
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>	
		



<jsp:include page="../common/footer.jsp"></jsp:include>


<script type="text/javascript">
const isOk = `<c:out value="${isOk}" />`;
if (isOk == 1) {
	alert('수정완료');
}
let bnoVal = `<c:out value="${bdto.bvo.bno}" />`;
let email = `<c:out value = "${authEmail}"/>`;
console.log(email);

</script>
<script type="text/javascript" src="/resources/js/boardComment.js"></script>
<script type="text/javascript">
getCommentList(bnoVal);
console.log("in");
</script>

</body>
</html>