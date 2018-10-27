<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">New Account</h3>
	</div>
	<s:form method="post" commandName="account" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/superadmin/account/create.html">
		<div class="box-body">
			<div class="form-group">
				<label for="txtproducerName">User Name</label>
				<i style="color: red">${used }</i>
				<s:input type="text" class="form-control" path="userName"
					required="required" />
			</div>
			<div class="form-group">
				<label for="txtproducerName">Password</label>
				<s:input type="password" class="form-control" path="password"
					id="password" required="required" />
			</div>
			<div class="form-group">
				<label for="txtproducerName">Retype Password</label> <s:password path="" class="form-control" id="confirm_password"
					required="required" />
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Name</label>
				<s:input type="text" class="form-control" path="name"
					required="required" />
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Email</label>
				<s:input type="email" class="form-control" path="email"
					required="required" />
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Phone Number</label>
				<s:input type="text" class="form-control" path="phoneNumber"
					required="required" />
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">Address</label>
				<s:input type="text" class="form-control" path="address"
					required="required"/>
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">Status</label><br>
				<s:checkbox path="enabled"
					required="required"/>
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">Photo</label> 
				<s:input path="" type="file" name="image" accept="image/gif, image/jpeg, image/png"/>
			</div>
			<div class="form-group">
				<label>Role</label> 
				<s:select name="roleName" path="">
					<c:forEach var="r" items="${role }">
						<option name="roleName" value="${r.id }">${r.name }</option>
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
