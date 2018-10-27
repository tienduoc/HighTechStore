<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="page home page-template-default">
	<div id="content" class="site-content" tabindex="-1">
		<div class="container">


			<nav class="woocommerce-breadcrumb">
				<a href="home.html">Home</a><span class="delimiter"><i
					class="fa fa-angle-right"></i></span>Register
			</nav>

			<div id="primary" class="content-area">
				<main id="main" class="site-main">
				<article class="page type-page status-publish hentry">
					<header class="entry-header">
						<h1 itemprop="name" class="entry-title">Register</h1>
					</header>
					<!-- .entry-header -->

					<s:form method="post" commandName="account"
						enctype="multipart/form-data"
						action="${pageContext.request.contextPath }/user/register.html">



						<div class="woocommerce-billing-fields">

							<p id="first_name_field"
								class="form-row form-row form-row-first validate-required">
								<label class="" for="first_name">Username <abbr
									title="required" class="required">*</abbr>
								</label>
								<i style="color: red">${used }</i>
								<s:input type="text" path="userName" name="first_name"
									required="required" class="input-text " pattern="[a-z]{1,15}"
									title="Username should only contain lowercase letters. e.g. john" />
							</p>

							<p id="last_name_field"
								class="form-row form-row form-row-last validate-required">
								<label for="last_name">Full Name <abbr title="required"
									class="required">*</abbr>
								</label>
								<s:input type="text" path="name" name="last_name"
									class="input-text " required="required" />
							</p>
							<div class="clear"></div>

							<p class="form-row form-row form-row-first validate-required">
								<label class="" for="first_name">Password <abbr
									title="required" class="required">*</abbr>
								</label>
								<s:input type="password" path="password" class="input-text "
									id="password" required="required"
									pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
									title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" />
							</p>

							<p class="form-row form-row form-row-last validate-required">
								<label class="" for="first_name">Confirm Password <abbr
									title="required" class="required">*</abbr>
								</label> <input type="password" id="confirm_password"
									class="input-text " required="required" />
							</p>
							<div class="clear"></div>


							<p id="email_field"
								class="form-row form-row form-row-first validate-required validate-email">
								<label class="" for="email">Email Address <abbr
									title="required" class="required">*</abbr>
								</label>
								<s:input type="email" path="email" name="email"
									required="required" class="input-text " />
							</p>

							<p id="phone_field"
								class="form-row form-row form-row-last validate-required validate-phone">
								<label class="" for="phone">Phone <abbr title="required"
									class="required">*</abbr>
								</label>
								<s:input type="tel" id="phone" path="phoneNumber" name="phone"
									required="required" class="input-text " />
							</p>
							<div class="clear"></div>

							<p id="address_1_field"
								class="form-row form-row form-row-wide address-field validate-required">
								<label class="" for="address_1">Address <abbr
									title="required" class="required">*</abbr>
								</label>
								<s:input type="text" path="address" required="required"
									name="address_1" class="input-text " />
							</p>
							<div class="clear"></div>
							<div name="photo">
								<p class="form-row">
									<label class="" for="address_1">Photo</label>
								</p>
								<input type="file" name="image" accept="image/gif, image/jpeg, image/png"><br>
								<p>* Max picture size below 500kb</p>
							</div>

							<div class="clear"></div>

						</div>
						<div class="woocommerce-checkout-payment" id="payment">

							<div class="form-row place-order">

								<p class="form-row terms wc-terms-and-conditions">
									<input type="checkbox" id="terms" name="terms"
										class="input-checkbox" required="required" value="0">
									<label class="checkbox" for="terms">I've read and
										accept the <a target="_blank"
										href="${pageContext.request.contextPath }/home/temp.html">terms
											&amp; conditions</a> <span class="required">*</span>
									</label> <input type="hidden" value="1" name="terms-field">
								</p>

								<input type="submit" value="Register" class="button alt">
							</div>
						</div>
					</s:form>
				</article>
				</main>
				<!-- #main -->
			</div>
			<!-- #primary -->
		</div>
		<!-- .container -->
	</div>
</div>