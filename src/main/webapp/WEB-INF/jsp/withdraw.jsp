<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Withdraw</h1>
<c:if test="${not empty msg}">
    <h4 style="color: red">${msg}</h4>
</c:if>
<table>
    <form:form method="post" action="submitWithdraw">
        <input type="radio" name="withdraw-value" value="10" id="withdraw-value"> $10<BR>
        <input type="radio" name="withdraw-value" value="50" id="withdraw-value"> $50<BR>
        <input type="radio" name="withdraw-value" value="100" id="withdraw-value"> $100<BR>
        <div>
            <input type="radio" name="withdraw-value" value="other"> $ <input type="number" id="other-value" value=0 name="other-value">
        </div>
        <BR>
        <input type="submit" value="Submit">
    </form:form>
    <tr>
        <td><a href="/">Exit</a></td>
    </tr>
    <tr>
    </tr>
</table>
<tr>
</tr>
<br/>
<%--<a href="empform">Add New Employee</a>--%>