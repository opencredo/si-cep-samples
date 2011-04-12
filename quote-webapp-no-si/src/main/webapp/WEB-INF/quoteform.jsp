<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Get a Quote</h2>
<form:form commandName="getQuotesCommand" action="getquote">
        <table>
            <tr><td>Applicant Age</td><td><form:input path="age" /></td></tr>
            <tr><td>Annual mileage</td><td><form:input path="annualMileage" /></td></tr>
            <tr><td>Years of no claims bonus</td><td><form:input path="yearsNoClaims" /></td></tr>
            <tr><td>Car regsitration</td><td><form:input path="carReg" /></td></tr>
            <tr><td>Years of no claims bonus</td><td><form:input path="makeAndModel" /></td></tr>
            <tr><td>Car value</td><td><form:input path="carValue" /></td></tr>
            <tr><td><input type="submit" value="Submit" /></td><td></td></tr>
        </table>
</form:form>



<!-- public int annualMileage;

    public int yearsNoClaims;

    public String carReg;

    public String makeAndModel;

    public double carValue;
-->

</body>
</html>