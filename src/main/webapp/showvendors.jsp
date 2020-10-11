<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vendors</title>
    <link rel="stylesheet" type="text/css" href="resources/styles.css">
</head>
<body>
    <h1>Vendors</h1>
    <table>
        <thead>
            <tr>
                <th>Country</th><th>Brand</th><th>Show Products</th>
            </tr>
        </thead>
        <tbody>
            <jsp:useBean id="vendorBean" scope="request" type="beans.VendorBean"/>
            <c:forEach items="${vendorBean.vendors}" var="c">
                <tr>
                    <td>${c.country.name}</td>
                    <td>${c.name}</td>
                    <td><form target="_blank" action="watches.html" method="post">
                        <input type="hidden" name="id" value=${c.id}>
                        <input type="submit" value="Click"></form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
