<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Account Detail</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
<s:form method="post" commandName="account"
            action="${pageContext.request.contextPath }/admin/account/create.html">
		<div class="box-body">
			<div class="form-group">
					<label for="txtpayment">userName</label>
				<s:input type="text" path="userName" required="required"
					class="form-control" id="txtpayment" value="${account.userName }" readonly="true" />
			</div>
			<div class="form-group">
				<label>name</label>
				<s:input type="text" path="name" readonly="true"
					class="form-control"  value="${account.name }" />
			</div>
			<div class="form-group">
				<label>address</label>
				<s:input type="text" path="address" readonly="true"
					class="form-control"  value="${account.address }" />
			</div>
			<div class="form-group">
				<label>phoneNumber</label>
				<s:input type="text" path="phoneNumber" readonly="true"
					class="form-control"  value="${account.phoneNumber }" />
			</div>
			<div class="form-group">
				<label>email</label>
				<s:input type="text" path="email" readonly="true"
					class="form-control"  value="${account.email }" />
			</div>
			<div class="form-group">
				<label>joinTime</label>
				<s:input type="text" path="joinTime" readonly="true"
					class="form-control"  value="${account.joinTime }" />
			</div>
			<div class="form-group">
				<label>photo</label>
				<img width="100" height="100" src="${pageContext.request.contextPath }/assets/user/images/account/${account.photo }"
					 />
			</div>			
			<div class="form-group">
				<label>enabled</label>
				<s:input type="text" path="enabled" readonly="true"
					class="form-control"  value="${account.enabled }" />
			</div>
			</div>
	</s:form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>
