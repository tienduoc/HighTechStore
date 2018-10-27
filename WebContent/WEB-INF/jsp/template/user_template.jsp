<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="tags" uri="categoryTag"%>
<%@ taglib prefix="tagss" uri="producerTag"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>High Tech Store</title>
<link rel="icon" href="${pageContext.request.contextPath }/assets/favicon.ico" type="image/x-icon">
<style>
#myBtn {
	display: none;
	position: fixed;
	bottom: 20px;
	right: 30px;
	z-index: 99;
	border: none;
	outline: none;
	background-color: red;
	color: white;
	cursor: pointer;
	padding: 15px;
	border-radius: 50%;
}

#myBtn:hover {
	background-color: #555;
}
</style>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/user/css/bootstrap.min.css"
	media="all" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/user/css/font-awesome.min.css"
	media="all" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/user/css/animate.min.css"
	media="all" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/user/css/font-electro.css"
	media="all" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/user/css/owl-carousel.css"
	media="all" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/user/css/style.css"
	media="all" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/user/css/colors/color.css"
	media="all" />

<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,700italic,800,800italic,600italic,400italic,300italic'
	rel='stylesheet' type='text/css'>

<link rel="shortcut icon"
	href="${pageContext.request.contextPath }/assets/user/images/fav-icon.png">
</head>

<body
	class="home page-template page-template-template-homepage-v3 full-color-background">
	<button onclick="topFunction()" id="myBtn" title="Go to top">
		<i class="fa fa-chevron-up fa-2x" aria-hidden="true"></i>
	</button>
	<div id="page" class="hfeed site">

		<div class="top-bar">
			<div class="container">
				<nav>
					<ul id="menu-top-bar-left"
						class="nav nav-inline pull-left animate-dropdown flip">
						<li class="menu-item animate-dropdown"><a
							title="Welcome to Worldwide Electronics Store"
							href="${pageContext.request.contextPath }/home.html">Welcome
								to High Tech Store</a></li>
					</ul>
				</nav>

				<nav>
					<ul id="menu-top-bar-right"
						class="nav nav-inline pull-right animate-dropdown flip">
						<li class="menu-item animate-dropdown"><a title="My Account"
							href="${pageContext.request.contextPath }/home/myAccount.html"><i
								class="ec ec-user"></i>Welcome
								${pageContext.request.userPrincipal.name}</a></li>
						<c:choose>
							<c:when test="${pageContext.request.userPrincipal.name != null}">
								<a href="<c:url value="/j_spring_security_logout" />"><input
									style="padding: 3px 6px" type="button" value="Logout"></a>
							</c:when>
							<c:when test="${pageContext.request.userPrincipal.name == null}">
								<a href="${pageContext.request.contextPath }/logins.html"><input
									style="padding: 3px 6px" type="button" value="Login"></a>
							</c:when>
						</c:choose>
					</ul>
				</nav>
			</div>
		</div>
		<!-- /.top-bar -->

		<header id="masthead" class="site-header header-v3">
			<div class="container">
				<div class="row">

					<!-- ============================================================= Header Logo ============================================================= -->
					<div class="header-logo">
						<a  href="${pageContext.request.contextPath }/home.html"
							class="header-logo-link"> <img width="250px" height="80px"
							src="${pageContext.request.contextPath }/assets/user/images/logo.png" />
						</a>
					</div>
					<!-- ============================================================= Header Logo : End============================================================= -->

					<form class="navbar-search" method="post"
						action="${pageContext.request.contextPath }/product/search.html">
						<label class="sr-only screen-reader-text" for="search">Search
							for:</label>
						<div class="input-group">
							<input type="text"
								placeholder="Search any product you want to find" id="search"
								class="form-control search-field" name="keyword" value="" />
							<div class="input-group-btn">
								<button type="submit" class="btn btn-secondary">
									<i class="ec ec-search"></i>
								</button>
							</div>
						</div>
					</form>

					<ul
						class="navbar-mini-cart navbar-nav animate-dropdown nav pull-right flip">
						<li class="nav-item dropdown"><a href="cart.html"
							class="nav-link" data-toggle="dropdown"> <i
								class="ec ec-shopping-bag"></i> <c:set var="s" value="0"></c:set>
								<c:set var="a" value="0"></c:set> <c:forEach var="i"
									items="${sessionScope.cart }">
									<c:set var="s" value="${s + i.product.price * i.quantity }"></c:set>
									<c:set var="a" value="${a + i.quantity }"></c:set>
								</c:forEach> <span class="cart-items-count count">${a }</span> <span
								class="cart-items-total-price total-price"><span
									class="amount">${s }</span></span>
						</a>
							<ul class="dropdown-menu dropdown-menu-mini-cart">
								<li>
									<div class="widget_shopping_cart_content">
										<c:set var="s" value="0"></c:set>
										<ul class="cart_list product_list_widget ">
											<c:forEach var="i" items="${sessionScope.cart }">
												<c:set var="s" value="${s + i.product.price * i.quantity }"></c:set>
												<c:set var="a" value="${a + i.quantity }"></c:set>
												<li class="mini_cart_item"><a title="Remove this item"
													class="remove"
													href="${pageContext.request.contextPath }/cart/delete/${i.product.id }.html">×</a>
													<a
													href="${pageContext.request.contextPath }/product/detail/${i.product.id }.html">
														<c:forEach var="pt" items="${i.product.photos }">
															<c:if test="${pt.main == true}">
																<img
																	class="attachment-shop_thumbnail size-shop_thumbnail wp-post-image"
																	src="${pageContext.request.contextPath }/assets/user/images/products/${pt.url }"
																	alt="">
															</c:if>
														</c:forEach> ${i.product.name }
												</a> <span class="quantity">${i.quantity } × <span
														class="amount">$${i.product.price }</span></span></li>
											</c:forEach>
										</ul>
										<!-- end product list -->


										<p class="total">
											<strong>Subtotal:</strong> <span class="amount">$${s }</span>
										</p>


										<p class="buttons">
											<a class="button wc-forward"
												href="${pageContext.request.contextPath }/cart/index.html">View
												Cart</a> <a class="button checkout wc-forward"
												href="${pageContext.request.contextPath }/cart/beforeCheckOut.html">Checkout</a>
										</p>
									</div>
								</li>
							</ul></li>
					</ul>
					<ul class="navbar-wishlist nav navbar-nav pull-right flip">
						<li class="nav-item"><a href="wishlist.html" class="nav-link"><i
								class="ec ec-transport"></i><span> Free Ship</span></a></li>
					</ul>
				</div>
				<!-- /.row -->
			</div>
		</header>
		<!-- #masthead -->

		<nav class="navbar navbar-primary navbar-full yamm">
			<div class="container">
				<div class="clearfix">
					<button class="navbar-toggler hidden-sm-up pull-right flip"
						type="button" data-toggle="collapse" data-target="#header-v3">
						&#9776;</button>
				</div>

				<div class="collapse navbar-toggleable-xs" id="header-v3">
					<ul class="nav navbar-nav">
						<li class="menu-item"><a title="Home"
							href="${pageContext.request.contextPath }/home.html">Home</a></li>

						<tags:category />

						<li class="menu-item"><a title="Contact"
							href="${pageContext.request.contextPath }/home/contact.html">Contact
								Us</a></li>
						<li class="menu-item"><a title="My Account"
							href="${pageContext.request.contextPath }/home/myAccount.html">My
								Account</a></li>
					</ul>
				</div>
				<!-- /.collpase -->
			</div>
			<!-- /.-container -->
		</nav>
		<!-- /.navbar -->

		<!--****************************Content**************************-->
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
		<!--****************************/Content**************************-->

		<section class="brands-carousel">
			<h2 class="sr-only">Brands Carousel</h2>
			<div class="container">
				<div id="owl-brands"
					class="owl-brands owl-carousel unicase-owl-carousel owl-outer-nav">

					<tagss:producer />
					<!-- /.item -->

				</div>
				<!-- /.owl-carousel -->

			</div>
		</section>

		<footer id="colophon" class="site-footer">

			<div class="footer-newsletter">
				<div class="container">
					<div class="row">

						<h5 class="newsletter-title">
							<a href="${pageContext.request.contextPath }/home/contact.html"
								style="color: white;">Contact</a> us your shopping experience
						</h5>
						<span class="newsletter-marketing-text">...and next
							shopping will be <strong>Better </strong>
						</span>

					</div>
				</div>
			</div>

			<div class="footer-bottom-widgets">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-7 col-md-push-5">
							<div class="columns" style="position: absolute; right: 250px">
								<aside id="nav_menu-2" class="widget clearfix widget_nav_menu">
									<div class="body">
										<h4 class="widget-title">Find It Fast</h4>
										<div class="menu-footer-menu-1-container">
											<ul id="menu-footer-menu-1" class="menu">
												<tags:category />
											</ul>
										</div>
									</div>
								</aside>
							</div>
							<!-- /.columns -->
							<div class="columns" style="position: absolute; right: 50px">
								<aside id="nav_menu-4" class="widget clearfix widget_nav_menu">
									<div class="body">
										<h4 class="widget-title">Customer Care</h4>
										<div class="menu-footer-menu-3-container">
											<ul id="menu-footer-menu-3" class="menu">
												<li class="menu-item"><a
													href="${pageContext.request.contextPath }/home/myAccount.html">My
														Account</a></li>
												<li class="menu-item"><a title="Contact"
													href="${pageContext.request.contextPath }/home/contact.html">Contact
														Us</a></li>
											</ul>
										</div>
									</div>
								</aside>
							</div>


						</div>
						<!-- /.col -->

						<div
							class="footer-contact col-xs-12 col-sm-12 col-md-5 col-md-pull-7">
							<div class="footer-logo" align="center">
								<img
									src="${pageContext.request.contextPath }/assets/user/images/logo.png" />
							</div>
							<!-- /.footer-contact -->

							<div class="footer-call-us">
								<div class="media">
									<span class="media-left call-us-icon media-middle"><i
										class="ec ec-support"></i></span>
									<div class="media-body">
										<span class="call-us-text">Got Questions ? Call us
											24/7!</span> <span class="call-us-number">(+84) 028 3880 3888</span>
									</div>
								</div>
							</div>
							<!-- /.footer-call-us -->


							<div class="footer-address">
								<strong class="footer-address-title">Contact Info</strong>
								<address>440/30 Nguyễn Kiệm Road, Ward 4, Phu Nhuan
									District, Ho Chi Minh City, Vietnam</address>
							</div>
							<!-- /.footer-address -->

						</div>

					</div>
				</div>
			</div>

			<div class="copyright-bar">
				<div class="container">
					<div class="pull-left flip copyright">
						&copy; <a onclick="javascript:return confirm('Are you an Admin? Click OK if you are')"
								href="${pageContext.request.contextPath }/admin/index.html">High Tech Store</a> - All Rights Reserved
					</div>
					<div class="pull-right flip payment">
						<div class="footer-payment-logo">
							<ul class="cash-card card-inline">
								<li class="card-item"><img
									src="${pageContext.request.contextPath }/assets/user/images/footer/paypal.png"
									alt="" width="52"></li>
								<li class="card-item"><img
									src="${pageContext.request.contextPath }/assets/user/images/footer/cod.png"
									alt="" width="52"></li>
							</ul>
						</div>
						<!-- /.payment-methods -->
					</div>
				</div>
				<!-- /.container -->
			</div>
			<!-- /.copyright-bar -->
		</footer>
		<!-- #colophon -->
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/password.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/tether.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/bootstrap-hover-dropdown.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/owl.carousel.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/echo.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/wow.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/jquery.easing.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/jquery.waypoints.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/user/js/electro.js"></script>
	<script>
		// When the user scrolls down 20px from the top of the document, show the button
		window.onscroll = function() {
			scrollFunction()
		};

		function scrollFunction() {
			if (document.body.scrollTop > 20
					|| document.documentElement.scrollTop > 20) {
				document.getElementById("myBtn").style.display = "block";
			} else {
				document.getElementById("myBtn").style.display = "none";
			}
		}

		// When the user clicks on the button, scroll to the top of the document
		function topFunction() {
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	</script>
</body>
</html>
