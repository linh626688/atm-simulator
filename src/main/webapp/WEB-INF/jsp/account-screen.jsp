<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Welcome Account</h1>
<table>
    <tr>
        <td>Account Number :</td>
        <td>${account.accountNumber}</td>
    </tr>
    <tr>
        <td>Name :</td>
        <td>${account.name}</td>
    </tr>
    <tr>
        <td>Balance :</td>
        <td>${account.balance}</td>
    </tr>
    <tr>
        <td><a href="transaction-log">Latest 10 transactions</a></td>
    </tr>
    <tr>
        <td><a href="withdraw">Withdraw</a></td>
    </tr>
    <tr>
        <td><input type="submit" value="FundTransfer"/></td>
    </tr>
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