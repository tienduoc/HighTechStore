<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content" class="site-content" tabindex="-1">
	<div class="container">


		<nav class="woocommerce-breadcrumb">
			<a href="home.html">Home</a> <span class="delimiter"><i
				class="fa fa-angle-right"></i></span> Login
		</nav>
		<!-- .woocommerce-breadcrumb -->

		<div id="primary" class="content-area">
			<main id="main" class="site-main">
			<article id="post-8" class="hentry">

				<div class="entry-content">
					<div class="woocommerce">
						<div class="customer-login-form">
							<span class="or-text">or</span>

							<div class="col2-set" id="customer_login">

								<div class="col-1">


									<h2>Login</h2>


									<form method="post" class="login"
										action="<c:url value='/j_spring_security_check' />">

										<p class="before-login-text">Welcome back! Sign in to your
											account</p>
										<p style="color: red">${msg }</p>
										<p class="form-row form-row-wide">
											<label for="username">Username <span class="required">*</span></label>
											<input type="text" class="input-text" name="username"
												id="username" value="" required="required" />
										</p>

										<p class="form-row form-row-wide">
											<label for="password">Password <span class="required">*</span></label>
											<input class="input-text" type="password" name="password"
												id="password" required="required" />
										</p>

										<p class="form-row">
											<input class="button" value="Login" name="login"
												type="submit">
										</p>

										<p class="lost_password">
											Lost your password?<a
												href="${pageContext.request.contextPath }/home/contact.html">
												Contact us </a>to receive again by email in 24h
										</p>

									</form>


								</div>
								<!-- .col-1 -->

								<div class="col-2">

									<h2>Register</h2>

									<div class="register">

										<p class="before-register-text">If you don't have account,
											create your very own account now</p>

										<p class="form-row">
											<a
												href="${pageContext.request.contextPath }/user/register.html">
												<input type="submit" class="button" name="register"
												value="Register" />
											</a>
										</p>

										<div class="register-benefits">
											<h3>Sign up today and you will be able to :</h3>
											<ul>
												<li>Speed your way through checkout</li>
												<li>Keep a record of all your purchases</li>
											</ul>
										</div>

									</div>

								</div>
								<!-- .col-2 -->

							</div>
							<!-- .col2-set -->

						</div>
						<!-- /.customer-login-form -->
					</div>
					<!-- .woocommerce -->
				</div>
				<!-- .entry-content -->

			</article>
			<!-- #post-## --> </main>
			<!-- #main -->
		</div>
		<!-- #primary -->


	</div>
	<!-- .col-full -->
</div>