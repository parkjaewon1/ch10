<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function chk() {
		if (frm.password.value != frm.password2.value) {
			alert("암호와 암호 확인이 다릅니다");
			frm.password.focus();
			frm.password.value = "";
			return false;
		}
	} 
</script></head><body>
<div class="container" align="center">
	<h2 class="text-primary">게시글 수정</h2>
<form action="${path}/update" method="post" name="frm" onsubmit="return chk()">
	<input type="hidden" name="num" value="${board.num}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="hidden" name="password2" value="${board.password}">
<table class="table table-bordered">
	<tr><td>제목</td><td><input type="text" name="subject" 
		required="required" autofocus="autofocus" value="${board.subject }"></td></tr>
<!--회원게시판에서는 사용안하고 id나 회원이름으로 대체
	회원 게시판 에서는 login하지 않으면 조회는 가능하지 만 입력/수정/삭제는 못하게 한다 -->
	<tr><td>작성자</td><td><input type="text" name="writer"
		required="required" value="${board.writer }"></td></tr>
	<tr><td>이메일</td><td><input type="email" name="email"
		required="required" value="${board.email }"></td></tr>
	<tr><td>암호</td><td><input type="password" name="password"
		required="required"></td></tr>
<!--여기 까지 회원게시판에서는 필요 없는 부분임 -->
	<tr><td>내용</td><td><textarea rows="5" cols="40" name="content"
		required="required">${board.content}</textarea></td></tr>
	<tr><td colspan="2" align="center"><input type="submit" value="확인"
		class="btn btn-warning">
</table>
</form>
</div>
</body>
</html>