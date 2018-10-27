<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Add new Producer</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
	<s:form method="post" commandName="producer"
		enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/admin/producer/create.html">
		<div class="box-body">
			<div class="form-group">
				<label for="txtproducerName">Name</label> <i style="color: red">${used }</i>
				<s:input type="text" class="form-control" path="name"
					required="required" placeholder="Producer Name" />
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Phone Number</label>
				<s:input type="text" class="form-control" path="phoneNumber"
					required="required" placeholder="Phone number" />
				<div class="form-group"></div>
				<div class="form-group">
					<label for="txtproducerAddress">Address</label>
					<s:input type="text" class="form-control" path="address"
						required="required" placeholder="Address" />
				</div>
				<div class="form-group">
					<label for="txtproducerAddress">Photo</label> <br>
					<s:input type="file" path="" name="image"
						accept="image/gif, image/jpeg, image/png" required="required" />
				</div>
				<div class="form-group">
					<label for="txtproducerAddress">Status</label>
					<s:checkbox path="status"/>
				</div>
			</div>
			<input type="submit" value="Save" class="btn btn-default" />
		</div>
	</s:form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>