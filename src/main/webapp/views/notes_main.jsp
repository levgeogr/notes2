<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<a href="/notes2">
    <h2 style="text-align: center;">Notes</h2>
</a>
<div class="list-group, center">
    <a href="/notes2/addnew">
        <button class="butt btn btn-warning">Add new note</button>
    </a>
    <br>
    <br>
    <form action="/notes2/search" method="post">
        <input type="submit" class="btn" value="Find" style="float: right"/>
        <div style="overflow: hidden; padding-right: .5em;">
            <input type="text" name="match" class="form-control" style="width: 100%;"/>
        </div>
    </form>
    <br>

    <c:choose>
        <c:when test="${fn:length(notes) > 0}">

            <c:forEach var="note" items="${notes}">
                <div class="list-group-item">
                    <a class="pull-xs-right" href="/notes2/delete?id=${note.id}">
                        <image src="../images/delete.svg"></image>
                    </a>
                    <a href="/notes2/shownote?id=${note.id}">
                            ${note.title == '' ? note.textbody : note.title}
                    </a>
                </div>
            </c:forEach>
        </c:when>

        <c:otherwise>
            <div>Ooops, there are no notes! :)</div>
        </c:otherwise>

    </c:choose>
</div>
</body>
</html>
