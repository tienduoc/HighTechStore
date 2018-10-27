<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Invoice</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/user/css/styleInvoice.css"
	media="all" />
<script type="text/javascript">
	function printDiv(divName) {
		var printContents = document.getElementById(divName).innerHTML;
		var originalContents = document.body.innerHTML;

		document.body.innerHTML = printContents;

		window.print();

		document.body.innerHTML = originalContents;
	}
</script>
</head>
<body>

	<div id="printableArea">
		<tiles:insertAttribute name="invoice"></tiles:insertAttribute>
		<div id="notices">
			<div>NOTICE:</div>
			<div class="notice">All request to modify your information of
				this invoice please contact us in 30 days.</div>
		</div>
		</main>
		<footer> Invoice was created on a computer and is valid
			without the signature and seal. </footer>
	</div>
	<input type="button" onclick="printDiv('printableArea')" value="Print" />
</body>
</html>