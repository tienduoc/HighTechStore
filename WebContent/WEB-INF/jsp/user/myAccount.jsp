<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page-template-default contact-v1">
	<div id="content" class="site-content" tabindex="-1">
		<div class="container">

			<nav class="woocommerce-breadcrumb">
				<a href="home.html">Home</a><span class="delimiter"><i
					class="fa fa-angle-right"></i></span>My Account
			</nav>


			<div id="primary" class="content-area">
				<main id="main" class="site-main">
				<article class="post-2508 page type-page status-publish hentry"
					id="post-2508">
					<div itemprop="mainContentOfPage" class="entry-content">
						<div class="map"
							style="width: 100vw; position: relative; margin-left: -50vw; left: 50%; margin-bottom: 3em;">
							<img alt=""
								src="${pageContext.request.contextPath }/assets/user/images/shopping.png">
						</div>
						<div class="vc_row-full-width vc_clearfix"></div>
						<div class="vc_row wpb_row vc_row-fluid">
							<div
								class="contact-form wpb_column vc_column_container vc_col-sm-9 col-sm-9">
								<div class="vc_column-inner ">
									<div class="wpb_wrapper">
										<div class="wpb_text_column wpb_content_element ">
											<div class="wpb_wrapper">
												<h2 class="contact-page-title">My Account</h2>
												<p>Edit your personal information if you want change
													something</p>
											</div>
										</div>
										<div lang="en-US" dir="ltr" id="wpcf7-f2507-p2508-o1"
											class="wpcf7" role="form">
											<div class="screen-reader-response"></div>
											<form class="wpcf7-form" method="post"
												enctype="multipart/form-data"
												action="${pageContext.request.contextPath }/home/myAccount.html">
												<div class="form-group row">
													<div class="col-xs-12 col-md-6">
														<label>Username *</label class="required"><br> <span
															class="wpcf7-form-control-wrap first-name"><input
															required="required" type="text"
															value="${account.userName }" pattern="[a-z]{1,99}"
															title="Username should only contain lowercase letters. e.g. john"
															class="wpcf7-form-control wpcf7-text
															wpcf7-validates-as-required input-text" name="userName"></input></span>
													</div>
													<div class="col-xs-12 col-md-6">
														<label>Full Name * </label class="required"><br> <span
															class="wpcf7-form-control-wrap last-name"><input
															required="required" type="text" value="${account.name }"
															class="wpcf7-form-control wpcf7-text wpcf7-validates-as-required input-text"
															name="name"></input></span>
													</div>
												</div>
												<div class="form-group row">
													<div class="col-xs-12 col-md-6">
														<label>Email *</label class="required"><br> <span
															class="wpcf7-form-control-wrap first-name"><input
															required="required" type="email"
															value="${account.email }"
															class="wpcf7-form-control wpcf7-text wpcf7-validates-as-required input-text"
															name="email"></input></span>
													</div>
													<div class="col-xs-12 col-md-6">
														<label>Phone Number *</label><br> <span
															class="wpcf7-form-control-wrap last-name"><input
															required="required" type="text"
															value="${account.phoneNumber }" maxlength="11" 
															class="wpcf7-form-control wpcf7-text wpcf7-validates-as-required input-text"
															name="phoneNumber"></input></span>
													</div>
												</div>
												<div class="form-group">
													<label>Address *</label class="required"><br> <span
														class="wpcf7-form-control-wrap subject"><input
														required="required" type="text"
														class="wpcf7-form-control wpcf7-text input-text"
														name="address" value="${account.address }"></input></span>
												</div>
												<div class="form-group">
													<label>Photo</label><br> <input type="file"
														name="image" accept="image/gif, image/jpeg, image/png"><br>
													<p>* Max picture size below 5Mb</p>
												</div>
												<div class="form-group clearfix">

													<p>
														<span class="wpcf7-form-control-wrap subject"> <input
															type="submit" value="Save"><a
															href="${pageContext.request.contextPath }/home/changePassword.html"><input
																type="button" value="Change Password"></a></span>
													</p>

												</div>
											</form>

										</div>
									</div>
								</div>
							</div>
							<div
								class="store-info wpb_column vc_column_container vc_col-sm-3 col-sm-3">
								<div class="vc_column-inner ">
									<div class="wpb_wrapper">
										<div class="wpb_text_column wpb_content_element ">
											<div class="wpb_wrapper">
												<h2 class="contact-page-title">Recent Orders</h2>
												<p>
													If your order have any problem or your want to cancel order
													before packaged, please <a
														href="${pageContext.request.contextPath }/home/contact.html"
														style="color: black;">Contact</a> us, we will quickly
													support you.
												</p>
												<ul class="list-unstyled operation-hours inner-right-md">
													<h3>20 Nearest Orders</h3>

													<c:forEach var="o" items="${order }">
														<li class="clearfix"><a
															href="${pageContext.request.contextPath }/home/orderDetail/${o.id }.html"><span
																class="day"><fmt:formatDate type="both"
																		dateStyle="short" timeStyle="short"
																		value="${o.timeCreation }" /></span></a><span
															class="pull-right flip hours">${o.orderStatus.name }</span></li>
													</c:forEach>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</article>
				</main>
				<!-- #main -->
			</div>
			<!-- #primary -->
		</div>
		<!-- .container -->
	</div>
</div>