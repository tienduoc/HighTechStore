<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Add new Supplier</h3>
	</div>
	<!-- form start -->
	<s:form method="post" commandName="supplier"
		action="${pageContext.request.contextPath }/admin/supplier/create.html">
		<div class="box-body">
			<div class="form-group">
				<label for="txtName">Name</label><i style="color: red">${used }</i>
				<s:input type="text" id="txtName" class="form-control" path="name"
					required="required" placeholder="Supplier Name" />
			</div>
			<div class="form-group">
				<label for="txtAddress">Address</label>
				<s:input type="text" id="txtAddress" class="form-control"
					path="address" required="required" placeholder="Supplier Address" />
			</div>
			<div class="form-group">
				<label for="txtphoneNumber">Phone Number</label>
				<s:input type="text" id="txtphoneNumber" class="form-control"
					path="phoneNumber" required="required"
					placeholder="Supplier number" />
			</div>
			<div class="form-group">
				<label>Status</label>
				<s:checkbox path="status" />
			</div>
			<div class="box-footer">
				<button type="submit" class="btn btn-primary" value="Save">Create</button>
			</div>
		</div>
	</s:form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>

</div>