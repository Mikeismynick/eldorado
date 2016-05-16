<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp"/>
<body>

<div class="container">

    <form role="form" action="${pageContext.request.contextPath}/" enctype="multipart/form-data" method="post">
        <label class="control-label">Select File</label>
        <input name="xml" id="input-1a" type="file" class="file" data-show-preview="false">
        <label class="control-label">Select filter by sum value</label>
        <input type="number" class="form-control" name="sumFilter" step="any" placeholder="type value...">
    </form>

    <p style="font-size: 150%"> Рассчеты: </p>
    <table class="table info-table table-striped">
        <tbody>
        <tr>
            <td>Сумма всех заказов</td>
            <td>${ordersTotalAmount} $</td>
        </tr>
        <tr>
            <td>Сумму максимального заказа</td>
            <td>${maxOrdersPrice} $</td>
        </tr>
        <tr>
            <td>Сумму минимального заказа</td>
            <td>${minOrdersPrice} $</td>
        </tr>
        <tr>
            <td>Количество заказов</td>
            <td>${ordersQty} шт.</td>
        </tr>
        <tr>
            <td>Средняя сумма заказа</td>
            <td>${averageOrdersPrice} $</td>
        </tr>
        <tr>
            <td>Клиент с максимальной суммой заказов</td>
            <td>${biggestCustomersName}, сумма заказов ${biggestCustomersOrdersSum} $</td>
        </tr>
        </tbody>
    </table>
    <c:choose>
        <c:when test="${sumFilter > 0}">
            <p style="font-size: 150%"> Клиенты с суммой заказов больше ${sumFilter} $: </p>
        </c:when>
        <c:otherwise>
            <p style="font-size: 150%"> Клиенты: </p>
        </c:otherwise>
    </c:choose>
    <table class="table info-table table-striped">
        <thead>
        <tr>
            <td>Имя</td>
            <td>ID</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customerList}">
            <tr>
                <td>${customer.name}</td>
                <td>${customer.id}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="fragments/footer.jsp"/>

