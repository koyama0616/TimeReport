<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dto.Staff"%>

<%
	String errorMsg = (String) request.getAttribute("errorMsg");
	String random_word = (String) request.getAttribute("random_word");

	Staff staff = (Staff) session.getAttribute("staff");
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
<title>勤怠管理</title>
</head>
<body>

	<!-- ヘッダー部分 -->

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark mt-3 mb-3">
		<div class="container">
			<a class="navbar-brand " href="AttendanceServlet">勤怠管理システム</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<%
				if (staff.getAuth().equals("ADMIN")) {
			%>

			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link" href="staff.jsp">社員管理</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">部署管理</a></li>
					<li class="nav-item"><a class="nav-link" href="calender.jsp">カレンダー管理</a>
					</li>
				</ul>

				<%
					}
				%>

				<ul class="navbar-nav">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <%=staff.getName()%>
					</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="#">設定</a> <a class="dropdown-item"
								href="LogoutServlet">ログアウト</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>

	<h1 class="text_center attendance">ようこそ</h1>


	<h3 class="text_center attendance"><%=staff.getName()%>さんお疲れ様です。
	</h3>

	<%
		if (errorMsg != null) {
	%>
	<%=errorMsg%><br>
	<%
		}
	%>

	<div class="majin2">
		<div class="majin">



			<!-- 出社 -->

			<form action="AdmissionServlet" method="post" >
				<input type="hidden" name="random_word" value="<%=random_word%>">
				<input type="submit" class="btn btn-success btn-lg" name="MySubmit" id="1"
					value="   出社   ">
			</form>

			<!-- 退社 -->

			<form action="LeavingServlet" method="post" >
				<input type="hidden" name="random_word" value="<%=random_word%>">
				<input type="submit" class="btn btn-danger btn-lg" name="MySubmit" id="2"
					value="   退社   ">
			</form>

		</div>
	</div>

	<br>
	<br>


	<!-- 勤怠状況 -->

	<div class="kintai">
		<form action="WorkManagementServlet" method="post">
			<input type="hidden" name="random_word" value="<%=random_word%>">
			<input type="submit" class="btn btn-info btn-lg" name="MySubmit"
				value="  勤怠状況  ">
		</form>
	</div>
	<br>

	<div class="logout">
		<form action="LogoutServlet" class="btn btn-secondary">
			<input type="submit" class="btn btn-secondary " value="ログアウト">
		</form>
	</div>

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