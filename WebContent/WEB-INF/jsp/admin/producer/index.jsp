<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="box">

	<div class="box-header" align="center">
		<h2>PRODUCER LIST DATA TABLE</h2>
	</div>
	<jsp:useBean id="pagedListHolder" scope="request"
		type="org.springframework.beans.support.PagedListHolder" />
	<c:url value="/admin/producer.html" var="pagedLink">
		<c:param name="ph" value="~" />
	</c:url>
	<!-- /.box-header -->
	<div class="box-body">
		<a
			href="${pageContext.request.contextPath }/admin/producer/create.html"><button
				type="submit" class="badge bg-green">Add New</button> </a> <br> <br>
		<form method="post"
			action="${pageContext.request.contextPath }/admin/producer/search.html">
			<button style="width: 85px; height: 27px;" class="fa fa-search">Search</button>
			<input type="text" name="keyword">
			<c:set var="s" value="0" />
			<c:forEach var="c" items="${pagedListHolder.pageList }">
				<c:set var="s" value="${s+1 }" />
			</c:forEach>
			<c:if test="${result == 1 }">
			  No result for keyword: <b style="color: red">${keyword }</b>
				<br>
			</c:if>
			<c:if test="${result == 2 }">
			  You have <b style="color: red">${s }</b> results for keyword: <b
					style="color: red">${keyword }</b>
				<br>
			</c:if>
			<br>


			<table id="example1" class="table table-bordered table-striped" >
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Phone</th>
						<th>Address</th>
						<th style="text-align: center;">Photo</th>
						<th>Active Status</th>
						<th style="text-align: center;">Option</th>
					</tr>

				</thead>
				<c:forEach var="p" items="${pagedListHolder.pageList }">
					<tbody>
						<tr>
							<td>${p.id }</td>
							<td>${p.name }</td>
							<td>${p.phoneNumber }</td>
							<td>${p.address }</td>
							<td style="text-align: center;"><img
								src="${pageContext.request.contextPath }/assets/user/images/producer/${p.photo }"
								width="200" height="60" /></td>
							<td>${p.status }</td>
							<td style="text-align: center;"><a
								href="${pageContext.request.contextPath }/admin/producer/update/${p.id }.html">
									<span class="badge bg-yellow">Edit</span>
							</a> || <a
								href="${pageContext.request.contextPath }/admin/producer/detail/${p.id }.html">
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
