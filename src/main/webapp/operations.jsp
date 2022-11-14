<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://workday.vlbb.ru/functions" %>
<html lang="ru">
<head>
    <title>Operations</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2 style="text-align: center">Operations</h2>
    <%--    <a href="actions?step=create" style="text-align: center">Add Action</a>--%>
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
        <tr class="${operation.excess ? 'excess' : 'normal'}">
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