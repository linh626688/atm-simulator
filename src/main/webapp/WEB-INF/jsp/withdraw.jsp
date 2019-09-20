<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Withdraw</h1>
<table>
    <form:form method="post" action="submitWithdraw">
        <input type="radio" name="withdraw-value" value="10" id="amount"> $10<BR>
        <input type="radio" name="withdraw-value" value="50" id="amount"> $50<BR>
        <input type="radio" name="withdraw-value" value="100" id="amount"> $100<BR>
        <div>
            <input type="radio" name="withdraw-value" value="other"> $ <input type="number" name="other-value" >
        </div><BR>
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