<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Login ATM</h1>
<form:form method="post" action="save">
    <table>
        <tr>
            <td>Account Number :</td>
            <td><form:input path="accountNumber"/></td>
        </tr>
        <tr>
            <td>PIN :</td>
            <td><form:input path="pin"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>

