<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Countries</title>
    <link rel="stylesheet" type="text/css" href="resources/styles.css">
    <script src="resources/script.js"></script>
</head>
<body>
    <h1>Countries</h1>
    <table>
        <thead>
        <tr>
            <th>Short name</th><th>Name</th><th>Show Brands</th>
        </tr>
        </thead>
        <tbody>
            <jsp:useBean id="countryBean" scope="request" type="beans.CountryBean"/>
            <c:forEach items="${countryBean.countries}" var="c">
                <tr>
                    <td>${c.shortName}</td>
                    <td>${c.name}</td>
<%--                    <td><button onclick="deleteCountry(${c.id})">Delete</button></td>--%>
                    <td><form target="_blank" action="vendors.html" method="post">
                        <input type="hidden" name="id" value=${c.id}>
                        <input type="submit" value="Click"></form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <span id="fieldForTesting"></span>
</body>
</html>