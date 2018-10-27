<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="box">
	<div class="box-header" align="center">
		<h2>ORDER LIST DATA TABLE</h2>
	</div>
	<jsp:useBean id="pagedListHolder" scope="request"
		type="org.springframework.beans.support.PagedListHolder" />
	<c:url value="/admin/orders.html" var="pagedLink">
		<c:param name="ph" value="~" />
	</c:url>
	<!-- /.box-header -->
	<div class="box-body">
		<br> <br>
		<form method="post"
			action="${pageContext.request.contextPath }/admin/orders/search.html">
			<button style="width: 85px; height: 27px;" class="fa fa-search">Search</button>
			<input type="text" name="username">
				<c:set  var="s" value="0"/>
			<c:forEach var="c" items="${pagedListHolder.pageList }">
				<c:set var="s" value="${s+1 }"/>
			</c:forEach>
			<c:if test="${result == 1 }">
			  No result for keyword: <b style="color: red">${keyword }</b> <br>
			</c:if>
			<c:if test="${result == 2 }">
			  You have <b style="color: red">${s }</b> results for keyword: <b style="color: red">${keyword }</b> <br>
			</c:if>
			<br>

			<table id="example1" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>timeCreation</th>
						<th>Payment</th>
						<th>Customer</th>
						<th>CheckMan</th>
						<th>Address</th>
						<th>Total</th>
						<th>Status</th>
						<th style="text-align: center;">Option</th>
					</tr>
				</thead>
				<c:forEach var="o" items="${pagedListHolder.pageList }">
					<tbody>
						<tr>
							<td>${o.id }</td>
							<td>${o.timeCreation }</td>
							<td>${o.payment.payment }</td>
							<td>${o.accountByCustomer.userName }</td>
							<td>${o.accountByCheckMan.userName }</td>
							<td>${o.address }</td>
							<td>${o.total }</td>
							<td>${o.orderStatus.name }</td>
							<td style="text-align: center;"><a
								href="${pageContext.request.contextPath }/admin/orders/update/${o.id }.html">
									<span class="badge bg-yellow">Edit</span>
							</a> || <a
								href="${pageContext.request.contextPath }/admin/orders/detail/${o.id }.html">
									<span class="badge bg-blue">Detail</span>
							</a></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</form>
	</div>
	<tg:paging pagedListHolder="${pagedListHolder}"
		pagedLink="${pagedLink}" />
</div>
