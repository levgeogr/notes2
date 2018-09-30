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
</head>
<body>
    <h2 style="text-align: center; color: red">ERROR</h2>
<br>

<div class="list-group, center">
    <div  style="text-align: center;">
        ${message}
    </div>

    <a href="/notes2" style="text-align: center;">
        Go to main page
    </a>
</div>

</body>
</html>
