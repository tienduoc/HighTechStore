<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="box">

	<div class="box-header" align="center">
		<h2>ACCOUNT DATA TABLE</h2>
	</div>
	<jsp:useBean id="pagedListHolder" scope="request"
		type="org.springframework.beans.support.PagedListHolder" />
	<c:url value="/superadmin/account.html" var="pagedLink">
		<c:param name="ph" value="~" />
	</c:url>
	<!-- /.box-header -->
	<div class="box-body">
		<a href="${pageContext.request.contextPath }/superadmin/account/create.html"><button
				type="submit" class="badge bg-green">Add New</button> </a>
				<br><br>
		<form method="post" action="${pageContext.request.contextPath }/superadmin/account/search.html">
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


			<table id="example1" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Username</th>
					<th>Name</th>
					<th>Address</th>
					<th>Phone Number</th>
					<th>Email</th>
					<th>Role</th>
					<th>Photo</th>
					<th>Enabled</th>
					<th style="text-align: center;">Option</th>
				</tr>

			</thead>
			<c:forEach var="a" items="${pagedListHolder.pageList }">
				<tbody>
					<tr>
						<td>${a.userName }</td>
						<td>${a.name }</td>
						<td>${a.address }</td>
						<td>${a.phoneNumber }</td>
						<td>${a.email }</td>
						<td><c:forEach items="${a.userRoles }" var="r">
									${r.role.name}
								</c:forEach></td>
						<td><img
							src="${pageContext.request.contextPath }/assets/user/images/account/${a.photo } "
							width="50" height="50" /></td>
						<td>${a.enabled }</td>
						<td style="text-align: center;"><a
							href="${pageContext.request.contextPath }/superadmin/account/update/${a.userName }.html">
								<span class="badge bg-yellow">Edit</span>
						</a> || <a
							href="${pageContext.request.contextPath }/superadmin/account/detail/${a.userName }.html">
								<span class="badge bg-blue">Detail</span>
						</a><a
							href="${pageContext.request.contextPath }/superadmin/account/changePassword/${a.userName }.html">
								<span class="badge bg-red">Change Password</span>
						</a></td>
					</tr>

				</tbody>
			</c:forEach>
		</table>
		</form>
	</div>
	<tg:paging pagedListHolder="${pagedListHolder}"
		pagedLink="${pagedLink}" />
	<!-- /.box-body -->
</div>
