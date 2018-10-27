<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Update Product Information</h3>
	</div>
	<form method="post"
		action="${pageContext.request.contextPath }/admin/product/update.html">
		<div class="box-body">
			<div hidden="hidden">
				<input type="text" required="required" name="id"
					value="${product.id }" />
			</div>
			<div class="form-group">
				<label>Name</label> <i style="color: red">${used }</i><input
					type="text" name="name" class="form-control" required="required"
					value="${product.name }">
			</div>
			<div class="form-group">
				<label>Producer</label> <select name="producerName">
					<option name="producerName" value="${product.producer.name }">${product.producer.name }</option>
					<c:forEach var="p" items="${producer }">
						<c:if test="${product.producer.name != p.name }">
							<option name="producerName" value="${p.name }">${p.name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>Category</label> <select name="categoryName">
					<option name="categoryName" value="${product.category.name }">${product.category.name }</option>
					<c:forEach var="c" items="${category }">
						<c:if test="${product.category.name != c.name}">
							<option name="categoryName" value="${c.name }">${c.name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>quantity</label> <input type="text" name="quantity"
					class="form-control" required="required" readonly="readonly"
					value="${product.quantity }">
				<div class="form-group"></div>
			</div>
			<div class="form-group">
				<label>description</label>
				<textarea rows="5" cols="20" name="description" class="form-control"
					required="required">${product.description }</textarea>
				<div class="form-group"></div>
			</div>
			<div class="form-group">
				<label>price</label> <input type="text" name="price"
					class="form-control" required="required" value="${product.price }">
			</div>
			<div class="form-group">
				<label>shortDescription</label>
				<textarea name="shortDescription" rows="5" cols="20"
					class="form-control">${product.shortDescription }</textarea>
			</div>
			<div class="form-group">
				<label>specification</label>
				<textarea name="specification" rows="5" cols="20"
					class="form-control">${product.specification }</textarea>
			</div>
			<label name="status">Status</label> <input type="checkbox"
				name="status" />
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<button class="btn btn-primary" type="submit">Update</button>
		</div>
	</form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>
