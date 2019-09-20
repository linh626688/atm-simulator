<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Summary</h1>
<table>
    <tr>
        <td>Account Number :</td>
        <td>${accountNumber}</td>
    </tr>
    <tr>
        <td>Date: </td>
        <td>${date}</td>
    </tr>
    <tr>
        <td>Withdraw :</td>
        <td>${amount}</td>
    </tr>
    <tr>
        <td>Balance :</td>
        <td>${balance}</td>
    </tr>
    <tr>
        <td><a href="account-screen">Account Screen</a></td>
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