<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Producer Detail</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
	<form role="form">
		<div class="box-body">
			<div class="form-group">
				<label for="txtproducerName">ID</label> <input type="text"
					class="form-control" id="txtproducerName" readonly="readonly"
					value="${producer.id }">
			</div>
			<div class="form-group">
				<label for="txtproducerName">Name</label> <input type="text"
					class="form-control" id="txtproducerName" readonly="readonly"
					value="${producer.name }">
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Phone Number</label> <input
					type="text" class="form-control" id="txtproducerPhonenumber"
					readonly="readonly" value="${producer.phoneNumber }">
				<div class="form-group"></div>
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">Address</label> <input type="text"
					class="form-control" id="txtproducerAddress" readonly="readonly"
					value="${producer.address }">
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">Image</label> <img
					src="${pageContext.request.contextPath }/assets/user/images/producer/${producer.photo }" />
			</div>
			<div class="form-group">
					<label for="txtproducerAddress">Status</label>
					<input type="text"
					class="form-control" id="txtproducerAddress" readonly="readonly"
					value="${producer.status }">
				</div>
		</div>

	</form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>