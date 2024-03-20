<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"
	integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd"
	crossorigin="anonymous"></script>
<title>COVID19</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="../images/icons/favicon.png/" />

<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">

<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">

<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">

<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<style>
input[type=number] {
	height: 45px;
	width: 45px;
	font-size: 25px;
	text-align: center;
	border: 1px solid #000000;
}

input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}
</style>
<script>
	function getCodeBoxElement(index) {
		return document.getElementById('codeBox' + index);
	}
	function onKeyUpEvent(index, event) {
		const eventCode = event.which || event.keyCode;
		if (getCodeBoxElement(index).value.length === 1) {
			if (index !== 6) {
				getCodeBoxElement(index + 1).focus();
			} else {
				getCodeBoxElement(index).blur();
				// Submit code
				console.log('submit code ');
			}
		}
		if (eventCode === 8 && index !== 1) {
			getCodeBoxElement(index - 1).focus();
		}
	}
	function onFocusEvent(index) {
		for (item = 1; item < index; item++) {
			const currentElement = getCodeBoxElement(item);
			if (!currentElement.value) {
				currentElement.focus();
				break;
			}
		}
	}
</script>
</head>
<div class="limiter">
	<div class="container-login100">
		<div class="wrap-login100">
			<div class="login100-pic js-tilt" data-tilt>
				<img src="../images/img-01.jpeg" alt="IMG"
					style="border-radius: 80%;">
			</div>
			<form class="login100-form validate-form"
				action="${pageContext.request.contextPath}/otp" method="post">
				<span class="login100-form-title"> Verification Code
					<p>please type verifiaction code sent to your gmail</p>
				</span>
				<div class="wrap-input100 validate-input">
					<input class="input100" type="otp" maxlength="6" id="otp"
						name="otp" placeholder="otp"> <span class="focus-input100"></span>
					<span class="symbol-input100"> <i class="fa fa-envelope"
						aria-hidden="true"></i>
					</span>
				</div>
				<br> <br></br>

				<button class="login100-form-btn">VERIFY</button>
			</form>
		</div>
	</div>
</div>


<script src="vendor/jquery/jquery-3.2.1.min.js"></script>

<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

<script src="vendor/select2/select2.min.js"></script>
<script src="vendor/tilt/tilt.jquery.min.js"></script>
<script>
	$('.js-tilt').tilt({
		scale : 1.1
	})
</script>

<script src="js/main.js"></script>

</body>
</html>