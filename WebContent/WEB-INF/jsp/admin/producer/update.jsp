<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Update Information of Producer</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
	<s:form method="post" commandName="producer"
		enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/admin/producer/update.html">

		<div class="box-body">
			<div hidden="hidden" class="form-group">
				<label for="txtproducerName">Id</label>
				<s:input type="text" path="id" id="txtproducerName"
					required="required" value="${producer.id }" />
			</div>
			<div class="form-group">
				<label for="txtproducerName">Name</label> <i style="color: red">${used }</i>
				<s:input type="text" path="name" required="required"
					class="form-control" id="txtproducerName" value="${producer.name }" />
			</div>
			<div class="form-group">
				<label for="txtproducerPhonenumber">Phone Number</label>
				<s:input type="text" path="phoneNumber" required="required"
					class="form-control" id="txtproducerPhonenumber"
					placeholder="Phone number" value="${producer.phoneNumber }" />
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">Address</label>
				<s:input type="text" path="address" required="required"
					class="form-control" id="txtproducerAddress" placeholder="Address"
					value="${producer.address }" />
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">Photo</label> <br>
				<s:input type="file" path="" name="image"
					accept="image/gif, image/jpeg, image/png" />
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">Status</label>
				<s:checkbox path="status" />
			</div>
		</div>

		<!-- /.box-body -->
		<div class="box-footer">
			<a
				href="${pageContext.request.contextPath }/admin/producer/update.html"><button
					type="submit" class="btn btn-primary">Update</button></a>
		</div>

	</s:form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>
