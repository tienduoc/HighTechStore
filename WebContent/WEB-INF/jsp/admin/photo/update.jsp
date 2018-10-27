<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Update Product Photo</h3>
	</div>

	<form method="post" enctype="multipart/form-data">
		<input type="hidden" name="photoID" value="${photo.id }">
		<div class="box-body">
			<div class="form-group">
				<label>Current Photo</label> <img width="200px" height="200px"
					alt=""
					src="${pageContext.request.contextPath }/assets/user/images/products/${photo.url }">
			</div>
			<div class="form-group">
				<label>Product</label> <input type="text" name="productName"
					readonly="readonly" value="${photo.product.name }">
			</div>

			<div class="form-group">
				<label>Photo</label> <i style="color: red">${used }</i> <input
					type="file" name="image" accept="image/gif, image/jpeg, image/png">
				<p>(* Max picture size below 5Mb)</p>

			</div>


			<c:if test="${photo.main == true}">
				<div class="form-group">
					<label>Main Photo</label> <input type="checkbox" name="main">
				</div>
			</c:if>
			<c:set var="i" value="0"></c:set>
			<c:forEach var="p" items="${photo.product.photos }">
				<c:if test="${p.main == true }">
					<c:set var="i" value="1"></c:set>
				</c:if>
			</c:forEach>
			<c:if test="${i != 1 }">
				<div class="form-group">
					<label>Main Photo</label> <input type="checkbox" name="main">
				</div>
			</c:if>

		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<button class="btn btn-primary" type="submit"
				formaction="${pageContext.request.contextPath }/admin/photo/update.html">Update</button>
		</div>
	</form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>
