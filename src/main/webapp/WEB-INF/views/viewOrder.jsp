<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Order</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style> 
</head>
<body>
    <h1>Order Details</h1>
    
    <c:if test="${empty order.orderDetails}">
        <p>Your order is empty.</p>
    </c:if>
    
    <c:if test="${not empty order.orderDetails}">
        <table>
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Subtotal</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="orderDetail" items="${order.orderDetails}">
                    <tr>
                        <td>${orderDetail.product.name}</td>
                        <td>${orderDetail.quantity}</td>
                        <td>$<fmt:formatNumber value="${orderDetail.product.price}" pattern="#,##0.00"/></td>
                        <td>$<fmt:formatNumber value="${orderDetail.quantity * orderDetail.product.price}" pattern="#,##0.00"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h2>Total: $<fmt:formatNumber value="${order.totalPrice}" pattern="#,##0.00"/></h2>
    </c:if>
    
    <a href="/index">Continue Shopping</a>
    <a href="/placeOrder">Place Order</a>
    
</body>
</html>