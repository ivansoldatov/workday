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
    <div style='text-align:center; width:100%'><a href="actions?step=create" style="text-align: center">Add Operation</a></div>
    <br>
    <table align="center" border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Time</th>
            <th>Description</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.actions}" var="action">
            <jsp:useBean id="meal" type="ru.vlbb.workday.model.OperationTo"/>
        <tr class="${action.excess ? 'excess' : 'normal'}">
            <td>
                    <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                    <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                    <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                    ${fn:formatDateTime(action.dateTime)}
            </td>
            <td>${action.description}</td>
            <td><a href="actions?step=update&id=${meal.id}">Update</a></td>
            <td><a href="actions?step=delete&id=${meal.id}">Delete</a></td>
        </tr>
        </c:forEach>
</section>
</body>


</html>