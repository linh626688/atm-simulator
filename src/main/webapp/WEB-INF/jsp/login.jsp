<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Login ATM</h1>
<c:if test="${not empty msg}">
    <h4 style="color: red">${msg}</h4>
</c:if>
<form:form id="loginForm" action="login" method="POST" modelAttribute="loginBean">
    <table>
        <tr>
            <td>Account Number :</td>
            <td>
                <form:input id="accountNumber" name="accountNumber" path="accountNumber" required="true"
                            onkeypress='return event.charCode >= 48 && event.charCode <= 57'
                            maxlength="6"/>
            </td>
        </tr>
        <tr>
            <td>PIN :</td>
            <td>
                <form:password id="pin" name="pin" path="pin" required="true"
                               onkeypress='return event.charCode >= 48 && event.charCode <= 57'
                               maxlength="6"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>


