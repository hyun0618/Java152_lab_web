<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lab 4</title>
</head>
<body>
    <header>
        <h1>Contents</h1>
        <h2><%= LocalDateTime.now()%></h2>
    </header>
    <main>
        <ul>
            <li><a href="ex1">첫번째 서블릿</a></li>
        </ul>
    </main>
</body>
</html>