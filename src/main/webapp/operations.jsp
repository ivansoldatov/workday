<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://workday.vlbb.ru/functions" %>
<html lang="ru">
<head>
    <title>Operations</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <hr/>
    <h2>Meals</h2>
    <form method="get" action="operations">
        <input type="hidden" name="action" value="filter">
        <dl>
            <dt>From Date (inclusive):</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt>To Date (inclusive):</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt>From Time (inclusive):</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt>To Time (exclusive):</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <hr/>
    <div style='text-align:center; width:100%'><a href="operations?action=create" style="text-align: center">Add
        Operation</a></div>
    <br>
    <table align="center" border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Start time</th>
            <th>End time</th>
            <th>Description</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.operations}" var="operation">
            <jsp:useBean id="operation" type="ru.vlbb.workday.to.OperationTo"/>
        <tr data-meal-excess="${operation.excess}">
            <td style="text-align: center">
                    ${fn:formatDate(operation.startDateTime)}
            </td>
            <td style="text-align: center"> ${fn:formatTime(operation.startDateTime)}</td>
            <td style="text-align: center"> ${fn:formatTime(operation.endDateTime)}</td>
            <td style="text-align: left">${operation.description}</td>
            <td><a href="operations?action=update&id=${operation.id}">Update</a></td>
            <td><a href="operations?action=delete&id=${operation.id}">Delete</a></td>
        </tr>
        </c:forEach>
</section>
</body>


</html>