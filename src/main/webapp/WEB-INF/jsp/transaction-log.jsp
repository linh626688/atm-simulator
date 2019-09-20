<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Latest 10 Transactions</h1>
<table border="2" width="70%" cellpadding="2">
    <tr>
        <th>Account Number</th>
        <th>Amount</th>
        <th>Time</th>
        <th>Type</th>
        <th>Ref</th>
    </tr>
    <c:forEach var="trans" items="${transactionList}">
        <tr>
            <td>${trans.accountNumber}</td>
            <td>${trans.amount}</td>
            <td>${trans.time}</td>
            <td>${trans.type}</td>
            <td>${trans.ref}</td>
        </tr>
    </c:forEach>
</table>
<td><a href="account-screen">Back</a></td>
<td><a href="/">Exit</a></td>

<br/>
<%--<a href="empform">Add New Employee</a>--%>