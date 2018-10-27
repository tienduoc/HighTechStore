<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<title>Detail</title>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Payment Detail</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
	<form role="form">
		<div class="box-body">
			<div class="form-group">
				<label for="txtproducerName">ID</label> <input type="text"
					class="form-control" id="txtproducerName" readonly="readonly"
					value="${payment.id }">
			</div>
			<div class="form-group">
				<label for="txtName">Payment</label> <input type="text"
					class="form-control" id="txtName" readonly="readonly"
					value="${payment.payment }">
			</div>
			<div class="form-group">
				<label for="txtName">Active Status</label> <input type="text"
					class="form-control" id="txtName" readonly="readonly"
					value="${payment.status }">
			</div>
		</div>
	</form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>
