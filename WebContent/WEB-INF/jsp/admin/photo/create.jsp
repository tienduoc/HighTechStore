<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">New Product Photo</h3>
	</div>
	<form method="post" enctype="multipart/form-data">
		<div class="box-body">

			<div class="form-group">
				<label>Product</label>
				<c:set var="i" value="0" />
				<c:set var="j" value="0" />
				<c:choose>
					<c:when test="${p.name == null }">
						<select name="productId">
							<c:forEach var="pr" items="${product }">
								<option value="${pr.id }">${pr.name }</option>
							</c:forEach>
						</select>
						<input type="submit" value="Choose"
							formaction="${pageContext.request.contextPath }/admin/photo/chooseProduct.html">
					</c:when>
					<c:when test="${p.name != null }">
						<select name="productId">
							<option value="${p.id }">${p.name }</option>
							<c:forEach var="pr" items="${product }">
								<c:if test="${pr.id != p.id}">
									<option value="${pr.id }">${pr.name }</option>
								</c:if>
							</c:forEach>
						</select>
						<c:set var="i" value="1" />
						<c:set var="j" value="${j+1 }" />
						<input type="submit" value="Choose"
							formaction="${pageContext.request.contextPath }/admin/photo/chooseProduct.html">
					</c:when>
				</c:choose>
			</div>

			<div class="form-group">
				<c:if test="${j != 0  }">
					<label>Photo</label>
					<i style="color: red">${used }</i>
					<input type="file" name="image"
						accept="image/gif, image/jpeg, image/png" required="required">
					<p>(* Max picture size below 5Mb)</p>
				</c:if>
			</div>

			<c:forEach var="photo" items="${p.photos }">
				<c:if test="${photo.main == true}">
					<c:set var="i" value="2" />
					<c:set var="j" value="${j+1 }" />
				</c:if>
				<c:if test="${photo.main == false}">
					<c:set var="j" value="${j+1 }" />
				</c:if>
			</c:forEach>
			<div class="form-group">
				<c:if test="${i == 1 }">
					<label>Main Photo</label>
					<input type="checkbox" name="main">
				</c:if>
			</div>
		</div>
		<!-- /.box-body -->
		<c:if test="${j != 0 }">
			<div class="box-footer">
				<button class="btn btn-primary" type="submit"
					formaction="${pageContext.request.contextPath }/admin/photo/create.html">
					<i class="fa fa-upload" aria-hidden="true"></i> Create
				</button>
			</div>
		</c:if>
	</form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>
