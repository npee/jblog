<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 공용 헤더 -->
<h1><a href="<c:url value="/" />">JBlog</a></h1>

<!-- 메뉴 영역 -->
<ul>
	<li><a href="<c:url value="/user/login" />">로그인</a></li>
	<li><a href="<c:url value="/user/join" />">회원가입</a></li>
<!--
<c:choose>	
	<c:when test="${empty authMember }">
		<li><a href="<c:url value="/members/login"/>">로그인</a></li>
		<li><a href="<c:url value="/members/join"/>">가입</a></li>
	</c:when>
	<c:otherwise>
		<li>${authMember.name }님, 환영합니다.</li>
		<li><a href="<c:url value="/members/logout" />">로그아웃</a></li>
	</c:otherwise>
</c:choose>
-->
</ul>