<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<title>Update</title>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Update Payment Information</h3>
	</div>
	<!-- form start -->
	<s:form method="post" commandName="payment"
		action="${pageContext.request.contextPath }/admin/payment/update.html">

		<div class="box-body">
			<div hidden="hidden">
				<s:input type="text" path="id" required="required"
					class="form-control" id="txtpayment" value="${payment.id }" />
			</div>
			<div class="form-group">
				<label for="txtpayment">Name</label>
				<i style="color: red">${used }</i>
				<s:input type="text" path="payment" required="required"
					class="form-control" id="txtpayment" value="${payment.payment }" />
			</div>
			<div class="form-group">
				<label for="txtpayment">Status</label>				
				<s:checkbox path="status"/>
			</div>
		</div>
		<div class="box-footer">
			<button class="btn btn-primary">Update</button>
		</div>
	</s:form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>