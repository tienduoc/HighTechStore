<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="box">

	<div class="box-header" align="center">
		<h2>IMPORT DATA TABLE</h2>
	</div>
	<jsp:useBean id="pagedListHolder" scope="request"
		type="org.springframework.beans.support.PagedListHolder" />
	<c:url value="/admin/import.html" var="pagedLink">
		<c:param name="ph" value="~" />
	</c:url>
	<!-- /.box-header -->
	<div class="box-body">
		<table id="example1" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Account</th>
					<th>TimeCreation</th>
					<th style="text-align: center;">Option</th>
				</tr>

			</thead>
			<c:forEach var="i" items="${pagedListHolder.pageList }">
				<tbody>
					<tr>
						<td>${i.id }</td>
						<td>${i.account.userName }</td>
						<td>${i.timeCreation }</td>
						<td style="text-align: center;"><a
							href="${pageContext.request.contextPath }/admin/import/detail/${i.id }.html">
								<span class="badge bg-blue">Detail</span>
						</a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
	<tg:paging pagedListHolder="${pagedListHolder}"
		pagedLink="${pagedLink}" />
	<!-- /.box-body -->
</div>
