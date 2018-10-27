<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Change Password</h3>
	</div>
	<form method="post"
		action="${pageContext.request.contextPath }/superadmin/account/changePassword.html">
		<div class="box-body">
			<div class="form-group">
				<label >Password</label>
				<input type="password" name="password" class="form-control" required="required" 
					id="password" />
			</div>
			<div class="form-group">
				<label >Retype Password</label>
				<input type="password" class="form-control" id="confirm_password" required="required" />
			</div>
			<input type="hidden" name="userName"
					value="${account.userName }"/>
			
			<input type="submit" value="Save" />
		</div>
	</form>
</div>
