<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Photo Detail</h3>
		<!-- form start -->
		<form role="form">
			<div class="box-body">
				<div class="form-group">
					<label for="txtID">ID</label> <input type="text"
						class="form-control" id="txtID" readonly="readonly"
						value="${photo.id }">
				</div>
				<div class="form-group">
					<label>Photo</label> <img alt=""
						src="${pageContext.request.contextPath }/assets/user/images/products/${photo.url }">
				</div>
				<div class="form-group">
					<label>URL</label> <input type="text" class="form-control"
						readonly="readonly" value="${photo.url }">
				</div>
				<div class="form-group">
					<label>Product</label> <input type="text" class="form-control"
						readonly="readonly" value="${photo.product.name }">
				</div>
				<div class="form-group">
					<label>Main</label> <input type="text" class="form-control"
						readonly="readonly" value="${photo.main }">
					<div class="form-group"></div>
				</div>

			</div>
			<!-- /.box-body -->

		</form>		
	</div>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>