<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="box">

	<div class="box-header" align="center">
		<h2>PAYMENT LIST DATA TABLE</h2>
	</div>
	<!-- /.box-header -->
	<div class="box-body">
		<a
			href="${pageContext.request.contextPath }/admin/payment/create.html"><button
				type="submit" class="badge bg-green">Add New</button> </a>
		<s:form method="post"
			action="${pageContext.request.contextPath }/admin/payment/search.html">
			<table id="example1" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Status</th>
						<th style="text-align: center;">Option</th>
					</tr>

				</thead>
				<c:forEach var="p" items="${listPayment }">
					<tbody>
						<tr>
							<td>${p.id }</td>
							<td>${p.payment }</td>
							<td>${p.status }</td>
							<td style="text-align: center;"><a
								href="${pageContext.request.contextPath }/admin/payment/update/${p.id }.html">
									<span class="badge bg-yellow">Edit</span>
							</a> || <a
								href="${pageContext.request.contextPath }/admin/payment/detail/${p.id }.html">
									<span class="badge bg-blue">Detail</span>
							</a></td>
						</tr>

					</tbody>
				</c:forEach>
			</table>
		</s:form>
	</div>
	<!-- /.box-body -->
</div>
