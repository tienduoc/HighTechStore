<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Update Account Information</h3>
	</div>
	<s:form method="post" commandName="account"
		enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/superadmin/account/update.html">
		<div class="box-body">
			<div class="form-group">
				<label>User Name</label>
				<s:input type="text" class="form-control" path="userName"
					value="${account.userName }" readonly="true" />
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Name</label>
				<s:input type="text" class="form-control" path="name"
					value="${account.name }" required="required" />
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Email</label>
				<s:input type="email" class="form-control" path="email"
					value="${account.email }" required="required" />
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Phone Number</label>
				<s:input type="text" class="form-control" path="phoneNumber"
					value="${account.phoneNumber }" required="required" />
			</div>
			<div class="form-group">
				<label>Address</label>
				<s:input type="text" class="form-control" path="address"
					value="${account.address }" required="required" />
			</div>

			<div class="form-group">
				<label>Status</label><br>
				<s:checkbox path="enabled" value="${account.enabled }"
					required="required" />
			</div>
			<div class="form-group">
				<label>Photo</label>
				<s:input hidden="hidden" type="text" path="photo" />
				<s:input path="" type="file" name="image"
					accept="image/gif, image/jpeg, image/png" />
			</div>
			<div class="form-group">
				<label>Role</label>

				<s:select name="roleId" path="">
					<c:forEach var="rr" items="${roles}">
						<s:option name="roleId" value="${rr.id }">${rr.name }</s:option>
					</c:forEach>
				</s:select>

			</div>
			
			<input type="submit" value="Save" />
		</div>
	</s:form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>
