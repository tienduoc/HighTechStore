<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="clearfix">
	<div id="logo">
		<img src="${pageContext.request.contextPath }/assets/user/images/logo.png" >
	</div>
	<h1>INVOICE #${order.id }</h1>
	<div id="company" class="clearfix">
		<div>High Tech Store</div>
		<div>
			440/30 Nguyễn Kiệm Road, Ward 4<br /> Phu Nhuan District, Ho Chi
			Minh City, Vietnam
		</div>
		<div>(84) 028 3880 3888</div>
		<div>contact@hightechstore.com</div>
	</div>
	<div id="project">
		<div>
			<span>Customer</span> ${order.accountByCustomer.name }
		</div>
		<div>
			<span>Address</span> ${order.address }
		</div>
		<div>
			<span>Email</span> ${order.accountByCustomer.email }
		</div>
		<div>
			<span>Date</span>
			<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
				value="${order.timeCreation }" />
		</div>
		<div>
			<span>Payment</span>
			${order.payment.payment }
		</div>
	</div>
</header>
<main>
<div align="center">
	<table>
		<thead>
			<tr>
				<th class="service">Product</th>
				<th class="desc"></th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Total</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="s" value="0"></c:set>
			<c:forEach var="i" items="${detail }">
				<c:set var="s" value="${s + i.price * i.quantity }"></c:set>
				<tr>
					<td class="service">${i.product.name }</td>
					<td class="desc"></td>
					<td class="unit">$${i.price }</td>
					<td class="qty">${i.quantity }</td>
					<td class="total">$${i.price* i.quantity}</td>
				</tr>
			</c:forEach>
			<tr>
				<td class="grand total"></td>
				<td class="grand total"></td>
				<td class="grand total"></td>
				<td class="grand total" align="right">GRAND TOTAL</td>
				<td class="grand total">$${s }</td>
			</tr>
		</tbody>
	</table>
</div>