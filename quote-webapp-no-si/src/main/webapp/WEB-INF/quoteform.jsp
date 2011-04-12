<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Hello World!</h2>
<form:form commandName="getQuotesCommand" action="getquote">
        <table>
            <tr>Age</tr><tr><form:input path="age" /> </tr>
            <tr><td><input type="submit" value="Submit" /></td><td></td></tr>
        </table>
</form:form>

</body>
</html>