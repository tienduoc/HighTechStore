<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Supplier Detail</h3>
		<!-- form start -->

		<form method="get">
			<div class="box-body">
				<div class="form-group">
					<label for="txtID">ID</label> <input type="text"
						class="form-control" id="txtID" readonly="readonly"
						value="${supplier.id }">
				</div>
				<div class="form-group">
					<label for="txtName">Name</label> <input type="text"
						class="form-control" id="Name" readonly="readonly"
						value="${supplier.name }">
				</div>
				<div class="form-group">
					<label for="PhoneNumber">Phone Number</label> <input type="text"
						class="form-control" id="PhoneNumber" readonly="readonly"
						value="${supplier.phoneNumber }">
					<div class="form-group"></div>
				</div>
				<div class="form-group">
					<label for="txtAddress">Address</label> <input type="text"
						class="form-control" id="txtAddress" readonly="readonly"
						value="${supplier.address }">
				</div>
				<div class="form-group">
					<label for="status">Status</label> <input type="text"
						class="form-control" id="txtAddress" readonly="readonly"
						value="${supplier.status }">
				</div>
			</div>

		</form>
		<div class="box-footer">
			<input type="submit" class="btn btn-default" onclick="goBack()"
				value="Back">
		</div>
	</div>
</div>