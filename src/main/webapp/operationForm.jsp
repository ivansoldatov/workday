<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://workday.vlbb.ru/functions" %>

<html>
<head>
    <title>Operation</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2 style="text-align: center">${param.action == 'create' ? 'Create operation' : 'Edit operation'}</h2>
    <jsp:useBean id="operation" type="ru.vlbb.workday.model.Operation" scope="request"/>
    <form method="post" action="operations">
        <input type="hidden" name="id" value="${operation.id}">
        <dl style="text-align: center">
            <dt>Date:</dt>
            <dd><input type="date" value="${operation.startDate}" name="startDate" required></dd>
        </dl>

        <dl style="text-align: center">
            <dt>Start time:</dt>
            <dd><input type="time" value="${operation.startTime}" name="startTime" required></dd>
        </dl>
        <dl style="text-align: center">
            <dt>End time:</dt>
            <dd><input type="time" value="${operation.endTime}" name="endTime" required></dd>
        </dl>
        <dl style="text-align: center">
        <dt>Description:</dt>
        <dd><input type="text" value="${operation.description}" size=57 name="description" required></dd>
        </dl>
        <div style="text-align: center">
        <button style="text-align: center" type="submit">Save</button>
        <button  onclick="window.history.back()" type="button">Cancel</button>
        </div>
    </form>
</section>
</body>
</html>
