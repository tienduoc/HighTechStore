<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>Dream</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="" />
<meta name="keywords" content="" />
<meta name="description" content="" />

<!--stylesheets-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/403/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/403/css/custom.css" />

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/apple-touch-icon-57-precomposed.png">
<script>
function goBack() {
    window.history.back();
}
</script>
</head>
<body>
	<div id="container" class="container-canvas">
		<div id="output" class="container"></div>
		<div id="vignette" class="overlay vignette"></div>
		<div id="noise" class="overlay noise"></div>
		<div id="ui" class="wrapper">
		<tiles:insertAttribute name="error_content"></tiles:insertAttribute>
			
		</div>
	</div>
	<div id="controls" class="controls"></div>
	<script type="text/javascript">
	//<![CDATA[		
		var ambient_value = '#880066'; <!-- change ambient color-->
		var diffuse_value = '#FF8800'; <!-- change diffuse color-->
	//]]>
	</script>
	<script type='text/javascript' src="${pageContext.request.contextPath }/assets/403/js/fss.js"></script>
	<script type='text/javascript' src="${pageContext.request.contextPath }/assets/403/js/custom.js"></script>
	<script type='text/javascript' src='${pageContext.request.contextPath }/assets/403/js/jquery.js'></script>
	<script type='text/javascript' src="${pageContext.request.contextPath }/assets/403/js/bootstrap.min.js"></script>
	<script type='text/javascript' src="${pageContext.request.contextPath }/assets/403/js/tooltip.js"></script>
</body>
</html>
