<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="box-header" align="center">
	<h2>CONTACT LIST DATA TABLE</h2>
</div>
<jsp:useBean id="pagedListHolder" scope="request"
	type="org.springframework.beans.support.PagedListHolder" />
<c:url value="/admin/contact.html" var="pagedLink">
	<c:param name="ph" value="~" />
</c:url>
<!-- /.box-header -->
<div class="box-body">
	<form method="post"
		action="${pageContext.request.contextPath }/admin/contact/search.html">
		<button style="width: 85px; height: 27px;" class="fa fa-search">Search</button>
		<input type="text" name="keyword">
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
					<th>Name</th>
					<th>Email</th>
					<th>Subject</th>
					<th>Description</th>
					<th>Seen</th>
					<th style="text-align: center;">Option</th>
				</tr>

			</thead>
			<c:forEach var="c" items="${pagedListHolder.pageList }">
				<tbody>
					<tr>
						<td>${c.id }</td>
						<td>${c.name }</td>
						<td>${c.email }</td>
						<td>${c.subject }</td>
						<td>${c.description }</td>
						<td><c:choose>
								<c:when test="${c.seen == true }">
									<input readonly="readonly" type="checkbox" checked="checked"
										readonly="readonly">
								</c:when>
								<c:when test="${c.seen == false }">
									<input type="checkbox" readonly="readonly" readonly="readonly">
								</c:when>
							</c:choose></td>
						<td style="text-align: center;"><a
							href="${pageContext.request.contextPath }/admin/contact/update/${c.id }.html">
								<span class="badge bg-yellow">Edit</span>
						</a> || <a
							href="${pageContext.request.contextPath }/admin/contact/detail/${c.id }.html">
								<span class="badge bg-blue">Detail</span>
						</a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</form>
</div>
<!-- /.box-body -->

