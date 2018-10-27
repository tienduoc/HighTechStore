<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Update Category Information</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
	<form method="post">
		<input type="hidden" name="id" value="${category.id }">
		<div class="box-body">
			<c:if test="${category.category.id != null}">
			<div class="form-group">
				<label>Parent Category</label> <select name="parent">
					<option name="parent" value="${category.category.id }">${category.category.name }</option>
					<c:forEach var="p" items="${parent }">
						<c:if test="${category.category.id != p.id}">
							<option name="parent" value=""></option>
							<option name="parent" value="${p.id }">${p.name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			</c:if>
			<div class="form-group">
				<label for="txtproducerName">Name</label><i style="color: red">${used }</i>
				<input type="text" class="form-control" name="Name"
					value="${category.name }" required="required" />
			</div>
			<div class="form-group">
				<label for="txtproducerName">Status</label> <i style="color: red">${used }</i>
				<input type="checkbox" name="status" value="${category.status }" />
			</div>

		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<input type="submit" class="btn btn-primary" value="Save"
				formaction="${pageContext.request.contextPath }/admin/category/update.html">
		</div>
	</form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>