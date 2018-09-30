<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Notes</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css"
          integrity="2hfp1SzUoho7/TsGGGDaFdsuuDL0LX2hnUp6VkX3CUQ2K4K+xjboZdsXyp4oUHZj"
          crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style>
        .center {
            width: 80%;
            margin: auto;
        }
    </style>
    <title>Note</title>
</head>
<body>
<a href="/notes2">
    <h2 style="text-align: center;">Notes</h2>
</a>
<br>
<br>
<div class="list-group, center">
    <h3 class="list-group-item" style="text-align: center;">${note.title}</h3>
    <br>
    <br>
    <div class="list-group-item">${note.textbody}</div>
</div>

</body>
</html>
