<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout page="Create post">
	<h1>Create post</h1>

	<form action="${mvc.uri('create-post')}" method="post">
		<label for="title">Title </label> <input id="title" type="text" name="title">
		<c:if test="${titleErrors != null}">
			<ul>
				<c:forEach items="${titleErrors}" var="msg">
					<li style="color: red;">${msg.message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<label for="content">Content </label> <textarea id="content" name="content"></textarea>
		<c:if test="${contentErrors != null}">
			<ul>
				<c:forEach items="${contentErrors}" var="msg">
					<li style="color: red;">${msg.message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">
		<button type="submit">Create post</button>
	</form>

	<hr>

	<a href="${mvc.uri('overview')}">Back to post</a>
</t:layout>