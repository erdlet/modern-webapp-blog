<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout page="Posts">
	<h1>All Posts</h1>

	<ul>
		<c:forEach items="${posts}" var="post">
			<li><a href="${mvc.uriBuilder('post').build(post.id)}">${post.title}</a></li>
		</c:forEach>
	</ul>

	<c:if test="${posts.isEmpty()}">
		<p>No posts created!</p>
	</c:if>

	<a href="${mvc.uri('new-post')}">Create new post</a>
</t:layout>