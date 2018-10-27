<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="box">

	<div class="box-header" align="center">
		<h2>Import Detail</h2>
	</div>
	<!-- /.box-header -->
	<div class="box-body">
		<form method="post">
			<table id="example1" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Product</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Supplier</th>
					</tr>

				</thead>
				
				<c:forEach var="d" items="${detail}">
					
					<tbody>
						<tr>
							<td>${d.id.importId }</td>
							<td>${d.product.name }</td>
							<td>${d.quantity }</td>
							<td>${d.price }</td>
							<td>${d.supplier.name }</td>							
						</tr>
					</tbody>
				</c:forEach>
			</table>			
		</form>
		<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
	</div>
	<!-- /.box-body -->
</div>
