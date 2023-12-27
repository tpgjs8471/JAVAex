<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.col-sm-12{
margin: auto;
}
.page{
margin-top : 35px;
display: flex;
justify-content: center;
}
.list{
border : 2px solid black;
border-radius : 4px;
width: 1000px;
height:100%;
margin:auto;
}
</style>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/nav.jsp"></jsp:include>
<div class="col-sm-12 col-md-6">
	<form action="/board/list" method="get">
		<div class="input-group mb-3">
			<c:set value="${ph.pgvo.type }" var="typed"></c:set>
			<select class="form-select" name="type" id="inputGroupSelect01">
				<option ${typed eq null ? 'selected' : '' }>선택</option>
				<option value="t" ${typed eq 't' ? 'selected' : '' }>제목</option>
				<option value="w" ${typed eq 'w' ? 'selected' : '' }>작성자</option>
				<option value="c" ${typed eq 'c' ? 'selected' : '' }>내용</option>
				<option value="tw" ${typed eq 'tw' ? 'selected' : '' }>내용+작성자</option>
				<option value="tc" ${typed eq 'tc' ? 'selected' : '' }>제목+내용</option>
				<option value="cw" ${typed eq 'cw' ? 'selected' : '' }>내용+작성자</option>
				<option value="tcw" ${typed eq 'tcw' ? 'selected' : '' }>전체</option>
			</select>
			
			<input class="form-control me-2" name="keyword" value="${ph.pgvo.keyword }" type="search" placeholder="Search" aria-label="Search">
	        <input type="hidden" name="pageNo" value="1">
	        <input type="hidden" name="qty" value="${ph.pgvo.qty}">
	        <button class="btn btn-outline-success" type="submit">
	        Search
	        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
	    	${ph.totalCount }
	    	<span class="visually-hidden">unread messages</span>
	 		</span>
        	</button>
		</div>
	</form>
</div>
	<div class="list">
	<table class="table table-hover">
		<thead class="table-dark">
			<tr>
				<th>#</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>파일수</th>
				<th>댓글수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td>${bvo.bno }</td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
					<td>${bvo.writer }</td>
					<td>${bvo.regAt }</td>
					<td>${bvo.readCount }</td>
					<td>${bvo.hasFile }</td>
					<td>${bvo.cmtQty }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
<div class="page">
<nav aria-label="Page navigation example">
  <ul class="pagination">
  <!-- 이전 -->
 
    <li class="page-item ${(ph.prev eq false) ? 'disabled' : '' }">
      <a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
   <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
    <li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a></li>
    </c:forEach>
    <!-- 다음 -->
    <li class="page-item ${(ph.next eq false) ? 'disabled' : '' } ">
      <a class="page-link" href="/board/list?pageNo=${ph.endPage + 1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>

<script type="text/javascript">
const isUp = `<c:out value="${isUp}" />`;
if (isUp > 0) {
	alert('게시글 등록 완료');
}
const isDel = `<c:out value="${isDel}" />`;
if (isDel == 1) {
	alert('게시글 삭제 완료');
}
</script>
</body>
</html>