<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Product Detail</h3>
		<!-- form start -->
		<form role="form">
			<div class="box-body">
				<div class="form-group">
					<label for="txtID">ID</label> <input type="text"
						class="form-control" id="txtID" readonly="readonly"
						value="${product.id }">
				</div>
				<div class="form-group">
					<label>Name</label> <input type="text" class="form-control"
						readonly="readonly" value="${product.name }">
				</div>
				<div class="form-group">
					<label>Producer</label> <input type="text" class="form-control"
						readonly="readonly" value="${product.producer.name }">
				</div>
				<div class="form-group">
					<label>Category</label> <input type="text" class="form-control"
						readonly="readonly" value="${product.category.name }">
				</div>
				<div class="form-group">
					<label>quantity</label> <input type="text" class="form-control"
						readonly="readonly" value="${product.quantity }">
					<div class="form-group"></div>
				</div>
				<div class="form-group">
					<label>description</label>
					<textarea class="form-control" readonly="readonly">${product.description }</textarea>
					<div class="form-group"></div>
				</div>
				<div class="form-group">
					<label>price</label> <input type="text" class="form-control"
						readonly="readonly" value="${product.price }">
					<div class="form-group"></div>
				</div>
				<div class="form-group">
					<label>shortDescription</label>
					<textarea class="form-control" readonly="readonly">${product.shortDescription }</textarea>
				</div>
				<div class="form-group">
					<label>specification</label>
					<textarea class="form-control" readonly="readonly">${product.specification }</textarea>
				</div>
				<label>Status</label>
				<c:choose>
					<c:when test="${product.status == true }">
						<input type="checkbox" checked="checked" readonly="readonly">
					</c:when>
					<c:when test="${product.status == false }">
						<input type="checkbox" readonly="readonly">
					</c:when>
				</c:choose>
			</div>
			<!-- /.box-body -->

		</form>
		<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
	</div>
</div>