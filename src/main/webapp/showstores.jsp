<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>War house data</title>
    <link rel="stylesheet" type="text/css" href="resources/styles.css">
</head>
<body>
    <h1>War house data</h1>
    <table>
        <thead>
            <tr>
                <th>Country</th><th>Brand</th><th>Name</th><th>Type</th><th>Price</th><th>Quantity</th><th>Date</th><th>Actual</th>
            </tr>
        </thead>
        <tbody>
        <jsp:useBean id="storeBean" scope="request" type="beans.StoreBean"/>
        <c:forEach items="${storeBean.stores}" var="c">
            <tr>
                <td>${c.watch.vendor.country.name}</td>
                <td>${c.watch.vendor.name}</td>
                <td>${c.watch.mark}</td>
                <td>${c.watch.type}</td>
                <td>${c.price}</td>
                <td>${c.quantity}</td>
                <td>${c.date}</td>
                <td>${c.actual}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
