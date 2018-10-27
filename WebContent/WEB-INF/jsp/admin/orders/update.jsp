<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Update Status of Order</h3>
	</div>
	<form method="post" action="${pageContext.request.contextPath }/admin/orders/update.html">
		<div class="box-body">
			<div hidden="hidden">
				<input type="text" required="required" name="id"
					value="${orders.id }" />
			</div>
			<div class="form-group">
				<label for="txtName">timeCreation</label>
				<input type="text" required="required" name="timeCreation"
					class="form-control" id="txtName" value="${orders.timeCreation }" readonly="readonly"/>
			</div>
			<div class="form-group">
				<label for="txtaddress">payment</label>
				<input type="text"required="required" name="payment"
					class="form-control" id="txtaddress" value="${orders.payment.payment }" readonly="readonly" />
			</div>
			<div class="form-group">
				<label for="txtphoneNumber">Customer account</label>
				<input type="text" required="required" name="Customeraccount"
					class="form-control" id="txtphoneNumber"
					value="${orders.accountByCustomer.userName }" readonly="readonly"/>
			</div>
			<div class="form-group">
				<label for="txtphoneNumber">CheckMan account</label>
				<input type="text" required="required" name="CheckManaccount"
					class="form-control" id="txtphoneNumber"
					value="${orders.accountByCheckMan.userName }" readonly="readonly"/>
			</div>
			<div class="form-group">
				<label for="txtphoneNumber">address</label>
				<input type="text" required="required"
					class="form-control" id="txtphoneNumber" name="address"
					value="${orders.address }" readonly="readonly"/>
			</div>
			<div class="form-group">
				<label for="txtphoneNumber">total</label>
				<input type="text" required="required"
					class="form-control" id="txtphoneNumber" name="total"
					value="${orders.total }" readonly="readonly"/>
			</div>			
			<div class="form-group">
				<label>Status</label>
				<select name="sttOption">
				<c:forEach var ="o" items="${status }">
					<option name="sttOption" value="${o.name }">${o.name }</option>
				</c:forEach>
				</select> 
			</div>
		</div>
		<div class="box-footer">
			<input type="submit"
					class="btn btn-primary" value="Update">
		</div>
		<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
	</form>
</div>
