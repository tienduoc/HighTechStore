<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Update Role</h3>
	</div>
	<s:form method="post" commandName="role"
		action="${pageContext.request.contextPath }/admin/role/update.html">
		<div class="box-body">
			<div hidden="hidden" class="form-group">
				<label for="txtproducerName">ID</label>
				<s:input type="text" path="id" class="form-control"
					id="txtproducerName" value="${role.id }" />
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Name</label><i style="color: red">${used }</i>
				<s:input type="text" path="name" class="form-control"
					id="txtproducerPhonenumber" value="${role.name }" />
				<div class="form-group"></div>
			</div>
			<div class="form-group">
				<label>Status</label>
				<s:checkbox path="status" value="${role.status }"/>
			</div>
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<button type="submit" class="btn btn-primary">Update</button>
		</div>
	</s:form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>