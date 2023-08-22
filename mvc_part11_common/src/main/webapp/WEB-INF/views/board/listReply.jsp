<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

	<h1>LIST REPLY</h1>
	<div>
		<h2>게시글 검색</h2>
		<form action="listReply" method="GET">
			<select name="searchType">
				<option value="n">-----------------------------</option>
				<option value="t">TITLE</option>
				<option value="c">CONTENT</option>
				<option value="w">WRITER</option>
				<option value="tc">TITLE &amp; CONTENT</option>
				<option value="cw">CONTENT &amp; WRITER</option>
				<option value="tcw">TITLE &amp; CONTENT &amp; WRITER</option>
			</select>
			<input type="text" name="keyword"/>
			<input type="submit" value="검색"/>
			<input type="button" id="newBtn" value="글작성"/>
		</form>
	</div>
	<br/>
	<table border="1">
		<tr>
			<th>BNO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th>VIEWCNT</th>
		</tr>
		<!-- 게시글 목록 출력 -->
		<c:choose>
			<c:when test="${!empty list}">
				<c:forEach var="board" items="${list}">
					<c:choose>
						<c:when test="${board.showboard == 'y'}">
							<tr>
								<td>${board.bno}</td>
								<td>
									<a href="readPage?bno=${board.bno}">
										<c:if test="${board.depth != 0}">
											<c:forEach var="i" begin="1" end="${board.depth}">
												&nbsp;&nbsp;&nbsp;
											</c:forEach>
											└>
										</c:if>
										<c:out value="${board.title}"/>
									</a>
								</td>
								<td>${board.writer}</td>
								<td>
									<f:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm"/>
								</td>
								<th>${board.viewcnt}</th>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th></th>
								<th>삭제된 게시물 입니다.</th>
								<th></th>
								<th>
							  		<f:formatDate value="${board.updatedate}" pattern="yyyy-MM-dd HH:mm"/>
								</th>
								<th></th>
							</tr>							
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<tr>
					<th colspan="5">
						<!-- PageMaker == pm -->
						<c:if test="${pm.prev}">
							<a href="listReply${pm.mkQueryStr(pm.startPage-1)}">&laquo;</a>
						</c:if>
						<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}">
							<a href="listReply${pm.mkQueryStr(i)}">[${i}]</a>
						</c:forEach>
						<c:if test="${pm.next}">
							<a href="listReply${pm.mkQueryStr(pm.endPage+1)}">&raquo;</a>
						</c:if>
					</th>
				</tr>	
			</c:when>
			<c:otherwise>
				<td colspan="5">등록된 게시물이 없습니다.</td>
			</c:otherwise>		
		</c:choose>
	</table>
</body>
</html>