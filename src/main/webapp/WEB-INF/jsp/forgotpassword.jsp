<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous"><script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script><script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd" crossorigin="anonymous"></script>
<title>Sign UP</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="../images/icons/favicon.png/"/>

<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">

<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">

<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">

<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

</head>
<body>
<div class="limiter">
<div class="container-login100">
<div class="wrap-login100">
<div class="login100-pic js-tilt" data-tilt>
<img src="../images/img-01.jpeg" alt="IMG" style="border-radius: 80%;">
</div>
<form class="login100-form validate-form"  action="${pageContext.request.contextPath}/forgotpassword"  method="post">
<span class="login100-form-title">
Forgot Password
</span>


<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
<input class="input100" type="email" id="username" name="email" maxlength="70" placeholder="Email">
<span class="focus-input100"></span>
<span class="symbol-input100">
<i class="fa fa-envelope" aria-hidden="true"></i>
</span>
</div>


<div class="wrap-input100 validate-input" data-validate = "Password is required">
<input class="input100" type="password" id="pwd" name="password" maxlength="15" placeholder="Password">
<span class="focus-input100"></span>
<span class="symbol-input100">
<i class="fa fa-lock" aria-hidden="true"></i>
</span>
</div>

<div class="wrap-input100 validate-input" data-validate = "Password is required">
<input class="input100" type="password" id="pwd" name="password1" maxlength="15" placeholder="Repeat Your Password">
<span class="focus-input100"></span>
<span class="symbol-input100">
<i class="fa fa-lock" aria-hidden="true"></i>
</span>
</div>


<div class="container-login100-form-btn">
<button class="login100-form-btn">
LOGIN
</button>
</div>


<div class="text-center">
   <c:if test="${param.error ne null}">
<div style="color: red">Invalid credentials.</div>
</c:if> <input type="hidden" name="${_csrf.parameterName}"
value="${_csrf.token}" />
<div class="text-center p-t-136">
<a class="txt2" href="${pageContext.request.contextPath}/signup">
Create your Account
<i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
</a>
</div>
</form>
</div>
</div>
</div>


<script src="vendor/jquery/jquery-3.2.1.min.js"></script>

<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

<script src="vendor/select2/select2.min.js"></script>
<script src="vendor/tilt/tilt.jquery.min.js"></script>
<script >
$('.js-tilt').tilt({
scale: 1.1
})
</script>

<script src="js/main.js"></script>

</body>
</html>