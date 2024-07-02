<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<section id="login-screen"
		class="d-flex flex-column align-items-center">
		<div class="welcome">
			<p class="fs-1 mb-0 text-center">Welcome.</p>
			<p class="fs-1 fw-bold mb-5 text-center">All-In Small</p>
		</div>

		<!-- adding method and name of each param -->
		<!-- adding action="login" -->

		<form action="/login" method="post"class="d-flex flex-column align-items-center" id="login-form">
			<input type="email" name="email" placeholder="Email Address"id="input-email" class="form-input"> 
				<input type="password"name="password" placeholder="Password" id="input-password"class="form-input mb-4"> 
				<c:if test="${not empty error}">
					<div style="color:red;">${error} </div>
				</c:if>
				 <a href="index.html" class="mb-5 fs-5">Forgot Password</a>
				 
		<!-- 	<button class="login-btn box-shadow border-linear-gad">
				<a href="/index.jsp" class="perfect-center"> Login </a> 
			</button> -->
				 
			<button class="login-btn box-shadow border-linear-gad">
				<input type="submit" value="Login" class="perfect-center" id="sub">
			</button>
		</form>

	</section>

</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>

</html>