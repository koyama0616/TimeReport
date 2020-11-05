<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

    <%
    String msg = (String) request.getAttribute("msg");
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet" href="./css/style.css">
<title>ログイン</title>

	<style>
	p.border {
	width : 	300px;
	margin:    30px auto;
	padding: 	20px;
	border: 	medium solid #000000;
	}
	</style>


</head>
<body>
<br>
<div class= "title">
	<h1 style = "text-align: center">勤怠管理</h1>

	<% if (msg != null) { %>
	<%= msg %><br>
	<% } %>
</div>

	<br>

	<div class="container">
        <div class="card card-container">
            <p id="profile-name" class="profile-name-card"></p>
            <form class="form-signin" action="LoginServlet" method="post">
                <span id="reauth-email" class="reauth-email"></span>
                メールアドレス：
                <input type="email" id="inputEmail" class="form-control" name="mail" placeholder="メールアドレスを入力してください" required autofocus >
                <br>
                パスワード：
                <input type="password" id="inputPassword" class="form-control" name="pass" placeholder="パスワードを入力してください" required >
                <div id="remember" class="checkbox">
                </div>
                <br>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Login</button>
            </form><!-- /form -->
        </div><!-- /card-container -->
    </div><!-- /container -->




<!--過去の産物
	<form action="LoginServlet" method="post">
		メールアドレス：<input type="email" name="mail"><br>
		パスワード：<input type="password" name="pass"><br>
		<br>
		<input type="submit" value="login">
	</form>
 -->

	<p class="border">テスト用アカウント<br>
	メールアドレス：bbbbb@bbb.co.jp<br>
	パスワード：bbbbbb<br>
	</p>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
</body>
</html>