<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Update Status of Feedback Contact</h3>
	</div>
	<form method="post"
		action="${pageContext.request.contextPath }/admin/contact/update.html">
		<div class="box-body">
			<div hidden="hidden" class="form-group">
				<label>ID</label> <input name="id" type="text" class="form-control"
					id="txtproducerName" value="${contact.id }" readonly="readonly" />
			</div>
			<div class="form-group">
				<label>Email</label> <input name="email" type="text"
					class="form-control" id="txtproducerPhonenumber"
					value="${contact.email }" readonly="readonly" />
			</div>
			<div class="form-group">
				<label>Name</label> <input name="name" type="text"
					class="form-control" id="txtproducerPhonenumber"
					value="${contact.name }" readonly="readonly" />
			</div>
			<div class="form-group">
				<label>Subject</label> <input name="subject" type="text"
					class="form-control" id="txtproducerPhonenumber"
					value="${contact.subject }" readonly="readonly" />
			</div>
			<div class="form-group">
				<label>Description</label> <input name="description" type="text"
					class="form-control" id="txtproducerPhonenumber"
					value="${contact.description }" readonly="readonly" />
			</div>
			<div class="form-group">
				<label>Seen</label> <input name="seen" type="checkbox" />
			</div>
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<button type="submit" class="btn btn-primary">Update</button>
		</div>
	</form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>