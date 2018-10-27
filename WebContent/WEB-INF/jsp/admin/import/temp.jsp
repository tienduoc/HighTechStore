<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="box">

	<div class="box-body">
		<form method="post">
			<table id="example1" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Category</th>
						<th>Producer</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Supplier</th>
						<th style="text-align: center;">Option</th>
					</tr>

				</thead>
				<c:set var="s" value="0"></c:set>
				<c:forEach var="p" items="${sessionScope.imports }">
					<c:set var="s" value="${s + p.product.price * p.quantity }"></c:set>
					<tbody>
						<tr>
							<td>${p.product.id }</td>
							<td>${p.product.category.name }</td>
							<td>${p.product.producer.name }</td>
							<td>${p.product.name }</td>
							<td><input type="number" value="${p.quantity }"
								name="quantity" min=1></td>
							<td>${p.product.price }</td>
							<td><select name="supplierName">
									<c:forEach var="sp" items="${supplier }">
										<option name="supplierName" value="${sp.name }">${sp.name }</option>
									</c:forEach>
							</select></td>
							<td align="center"><a
								href="${pageContext.request.contextPath }/admin/import/deleteTemp/${p.product.id }.html"><span
									class="badge bg-red">X</span></a></td>
						</tr>

					</tbody>
				</c:forEach>
			</table>
			<input type="submit" value="update"
				formaction="${pageContext.request.contextPath }/admin/import/updateTemp.html">
			<input type="submit" value="Save"
				formaction="${pageContext.request.contextPath }/admin/import/save.html">

		</form>
		<div class="box-footer">
			<input type="submit" class="btn btn-default" onclick="goBack()"
				value="Back">
		</div>
	</div>
	<!-- /.box-body -->
</div>
