<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Watches</title>
    <link rel="stylesheet" type="text/css" href="resources/styles.css">
</head>
<body>
    <h1>Watches</h1>
    <table>
        <thead>
            <tr>
                <th>Country</th><th>Brand</th><th>Name</th><th>Type</th><th>War house data</th>
            </tr>
        </thead>
        <tbody>
            <jsp:useBean id="watchBean" scope="request" type="beans.WatchBean"/>
            <c:forEach items="${watchBean.watches}" var="c">
                <tr>
                    <td>${c.vendor.country.name}</td>
                    <td>${c.vendor.name}</td>
                    <td>${c.mark}</td>
                    <td>${c.type}</td>
                    <td><form target="_blank" action="stores.html" method="post">
                        <input type="hidden" name="id" value=${c.id}>
                        <input type="submit" value="Click"></form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
