<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout page="Internal Server Error">
	<h1>Internal Server Error - something baaaaaad happened</h1>

	<pre>${msg}</pre>

	<a href="${mvc.uri('overview')}">Back to overview</a>
</t:layout>