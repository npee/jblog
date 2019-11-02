<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header id="blog-header">
	<h1>${authUser.userName }의 블로그 입니다.</h1>

	<ul>
		<c:choose>	
			<c:when test="${empty authUser }">
				<li style=""><a href="<c:url value="/user/login"/>">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="<c:url value="/user/logout" />">로그아웃</a></li>
				<li><a href="<c:url value="/" />">내블로그 관리</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</header>