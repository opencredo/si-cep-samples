<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Quotes</title></head>
<body>
<table>
    <c:forEach items="${quotes}"
               var="quote">
        <tr>
            <td><c:out value="${quote.providerName}"/></td>
            <td>£<c:out value="${quote.quotedAnnualPrice}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>