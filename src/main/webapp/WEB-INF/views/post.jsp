<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout page="Post: ${post.title}">
	<h1>Posts: ${post.title}</h1>
	<small>${post.publishedAt}</small>
	<pre>${post.content}</pre>

	<h2>Comments</h2>
	<ul>
		<c:forEach items="${post.comments}" var="comment">
			<li>
				<div>${comment.author} wrote on ${comment.publishedAt}:</div>
				<div>${comment.content}</div>
			</li>
		</c:forEach>
	</ul>

	<form action="${mvc.uriBuilder('create-comment').build(post.id)}" method="post">
		<label for="author">Author </label> <input id="author" type="text" name="author">
		<c:if test="${authorErrors != null}">
			<ul>
				<c:forEach items="${authorErrors}" var="msg">
					<li style="color: red;">${msg.message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<label for="content">Content </label> <input type="text" id="content" name="content">
		<c:if test="${contentErrors != null}">
			<ul>
				<c:forEach items="${contentErrors}" var="msg">
					<li style="color: red;">${msg.message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">
		<button type="submit">Save commen</button>
	</form>

	<hr>

	<a href="${mvc.uri('overview')}">Back to overview</a>&nbsp;|&nbsp;
	<a href="${mvc.uriBuilder('edit-post').build(post.id)}">Edit post</a>

	<form action="${mvc.uriBuilder('delete-post').build(post.id)}" method="post">
		<input type="hidden" name="_method" value="DELETE">
		<input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">
		<button type="submit" class="button error">Delete post</button>
	</form>
</t:layout>