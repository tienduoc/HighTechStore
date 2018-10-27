<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Order Detail</h3>
		<!-- form start -->
		<form method="get">
			<c:set var="s" value="0"></c:set>

			<table id="example1" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Product ID</th>
						<th>Product Name</th>
						<th>Quantity</th>
						<th>Price</th>
					</tr>
				</thead>
				<c:forEach var="detail" items="${orders }">
					<c:set var="s" value="${s + detail.price * detail.quantity }"></c:set>
					<tbody>
						<tr>
							<td>${detail.product.id }</td>
							<td>${detail.product.name }</td>
							<td>${detail.quantity }</td>
							<td>${detail.price }</td>

						</tr>
					</tbody>
				</c:forEach>
			</table>
			<div align="right">
				<h3>Total: $${s }</h3>
			</div>

			<!-- /.box-body -->
		</form>
		<div class="box-footer">
			<input type="submit" class="btn btn-default" onclick="goBack()"
				value="Back">
		</div>
	</div>
</div>