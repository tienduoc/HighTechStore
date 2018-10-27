<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Add new Product</h3>
	</div>
	<form method="post"
		action="${pageContext.request.contextPath }/admin/product/create.html">
		<div class="box-body">
			<div class="form-group">
				<label>Name</label> <i style="color: red">${used }</i><input
					type="text" name="name" class="form-control" required="required">
			</div>
			<div class="form-group">
				<label>Producer</label> <select name="producerName">
					<c:forEach var="p" items="${producer }">
						<option name="producerName" value="${p.name }">${p.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>Category</label> <select name="categoryName">
					<c:forEach var="c" items="${category }">
						<option name="categoryName" value="${c.name }">${c.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>Description</label>
				<textarea rows="5" cols="20" name="description" class="form-control"
					required="required"></textarea>
				<div class="form-group"></div>
			</div>
			<div class="form-group">
				<label>Price</label> <input type="number" step="0.01" name="price"
					class="form-control" required="required">
			</div>
			<div class="form-group">
				<label>shortDescription</label>
				<textarea name="shortDescription" rows="5" cols="20"
					class="form-control"></textarea>
			</div>
			<div class="form-group">
				<label>specification</label>
				<textarea name="specification" rows="5" cols="20"
					class="form-control"></textarea>
			</div>
			<label name="status">Status</label> <input type="checkbox"
				name="status" />
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<button class="btn btn-primary" type="submit">Create</button>
		</div>
	</form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>
