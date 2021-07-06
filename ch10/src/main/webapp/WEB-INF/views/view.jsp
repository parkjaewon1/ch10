<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() { 
		$('#rbdListDisp').load("${path}/replyList/bno/${board.num}")
		$('#boardDisp').load("${path}/list/pageNum/${pageNum}");
		$('#rInsert').click(function() {
/* 			var sendData = "bno="+frm1.bno.value+
				"&replyer="+frm1.replyer.value+
				"&replytext="+frm1.replytext.value; */
			var sendData = $('#frm1').serialize();
			$.post('${path}/rInsert', sendData, function(data) {
				alert("댓글이 작성 되었습니다");
				$('#rbdListDisp').html(data);
				frm1.replytext.value=""; // 댓글 지우기
			});	
		});
	});
</script></head><body>
<div class="container" align="center">
	<h2 class="text-primary">게시글 상세 내역</h2>
<table class="table table-striped table-bordered">
	<tr><td>제목</td><td colspan="3">${board.subject }</td></tr>
	<tr><td>작성자</td><td>${board.writer }</td>
		<td>이메일</td><td>${board.email }</td></tr>
	<tr><td>조회수</td><td>${board.readcount }</td>
		<td>작성일</td><td>${board.reg_date }</td></tr>
	<tr><td>내용</td><td colspan="3">	<pre>${board.content }</pre></td></tr>
	<tr><td colspan="4" align="center">
		<a href="${path}/list/pageNum/${pageNum}" class="btn btn-info">게시글 목록</a> 
		<a href="${path}/updateForm/num/${board.num}/pageNum/${pageNum}" 
			class="btn btn-warning">수정</a> 
		<!-- 	회원 게시글일 경우에는 deleteForm없이 삭제 확인후에 바로 삭제 -->
		<a href="${path}/deleteForm/num/${board.num}/pageNum/${pageNum}" 
			class="btn btn-danger">삭제</a> 
		<a href="${path}/insertForm/num/${board.num}/pageNum/${pageNum}" 
			class="btn btn-success">답글</a></td></tr> 
</table>
<!-- submit action이 없으면 자신(view)을 확번 더 수행 -->
<h2 class="text-primary">댓글 작성</h2>
<form action="" name="frm1" id="frm1">
	<input type="hidden" name="bno" value="${board.num }">
<table class="table table-hover">		
<!-- board.writer가 아니라 원래는 로그인 한 사람 이름(또는 ID) -->
	<tr><td>작성자</td><td><input type="text" name="replyer" 
		value="${board.writer }"></td><td>댓글</td>
		<td><textarea rows="3" cols="30" name="replytext"></textarea></td>
		<td><input type="button" value="댓글 입력" id="rInsert"></td>
</table></form>
<div id="rbdListDisp"></div>
<div id="boardDisp"></div>
</div>
</body>
</html>