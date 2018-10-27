<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Feedback Contact Detail</h3>
	</div>
	<form role="form">
		<div class="box-body">
			<div class="form-group">
				<label for="txtproducerName">ID</label> <input type="text"
					class="form-control" readonly="readonly" value="${contact.id }">
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">name</label> <input type="text"
					class="form-control" readonly="readonly" value="${contact.name }" />
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">subject</label> <input type="text"
					class="form-control" readonly="readonly"
					value="${contact.subject }" />
			</div>
			<div class="form-group">
				<label for="txtproducerAddress">description</label> <input
					type="text" class="form-control" readonly="readonly"
					value="${contact.description }" />
			</div>
			<div class="form-group">
				<label>Seen</label>
				<c:choose>
					<c:when test="${contact.seen == true }">
						<input type="checkbox" checked="checked" readonly="readonly">
					</c:when>
					<c:when test="${contact.seen == false }">
						<input type="checkbox" readonly="readonly">
					</c:when>
				</c:choose>
			</div>
		</div>
		<!-- /.box-body -->

	</form>
	<div class="box-footer">
		<input type="submit" class="btn btn-default" onclick="goBack()"
			value="Back">
	</div>
</div>