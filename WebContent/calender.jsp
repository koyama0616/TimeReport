<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.Workday_flagDTO"%>
<%@ page import="java.util.*, dto.Staff"%>

<%
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
<title>かりんだー</title>
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

<%if(staff.getAuth().equals("ADMIN")){%>

			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link" href="staff.jsp">アカウント一覧</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">部署一覧</a></li>
					<li class="nav-item"><a class="nav-link" href="calender.jsp">カレンダー管理</a>
					</li>
				</ul>

<% }%>

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

	<div class="container-fluid" style="border: 1px solid #ff6a00">
		<div class="row">
			<div class="col-4" id="column1">
				<h3>月を選択してください</h3>
				<form action="getMonthDays">
					<input type="month" name="month"> <input type="submit"
						class="btn btn-primary" value="月切替">
				</form>
				<p class="danger">${monthMessage}</p>
				<p class="danger">${daysMessage}</p>
				日付
				<%
					ArrayList<String> monthDayList = (ArrayList<String>) session.getAttribute("monthDaysList");
				%>
				<%
					if (monthDayList == null) {
				%>
				<%
					} else {
				%>
				<form action="GetDivision">
					<table class="table outline">
						<tr>
							<td>
								<table class="row" border="0">
									<tr>
										<%
											for (int i = 0; i < 10; i++) {
										%><td><input type="radio" name="date"
											value="<%=monthDayList.get(i)%>"><%=monthDayList.get(i)%></td>
										<%
											}
										%>
										<%
											if (monthDayList.size() == 31) {
										%>
										<td>&nbsp;</td>
										<%
											}
										%>
									</tr>
								</table>
							</td>

							<td>
								<table class="row" border="0">
									<tr>
										<%
											for (int i = 10; i < 20; i++) {
										%>
										<td><input type="radio" name="date"
											value="<%=monthDayList.get(i)%>"><%=monthDayList.get(i)%></td>
										<%
											}
										%>
										<%
											if (monthDayList.size() == 31) {
										%>
										<td>&nbsp;</td>
										<%
											}
										%>
									</tr>
								</table>
							</td>

							<td>
								<table class="row" border="0">
									<tr>
										<%
											for (int i = 20; i < monthDayList.size(); i++) {
										%><td><input type="radio" name="date"
											value="<%=monthDayList.get(i)%>"><%=monthDayList.get(i)%></td>
										<%
											}
										%>
										<%
											if (monthDayList.size() == 28) {
										%>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<%
											}
										%>
										<%
											if (monthDayList.size() == 29) {
										%>
										<td>&nbsp;</td>
										<%
											}
										%>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<input type="submit" class="btn btn-primary" value="出社、休業確認">
					<%
						}
					%>
				</form>
			</div>
			<%
				ArrayList<Workday_flagDTO> departmentList = (ArrayList<Workday_flagDTO>) request
						.getAttribute("departmentList");
			%>
			<%
				if (departmentList == null) {
			%>
			<%
				} else {
			%>
			<div class="col-8">
				<h3 class="text_center">${sessionScope.monthDays}</h3>
				<div class="row">
					<div class="col-5" id="column2">
						<h4 class="text_center">出社</h4>
						<%
							for (Workday_flagDTO wfd : departmentList) {
									if (wfd.isWorkflag() == true) {
						%>
						<form action="ChangeDivision">
							<p class="text_center">
								<input type="checkbox" name="openDep" value="<%=wfd.getName()%>"><%=wfd.getName()%>部&nbsp;
							</p>
							<%
								}
									}
							%>

					</div>
					<div class="col-2" id="column3">
						<div class="wrapp">
							<div class="text_center">
								<input type="image" name="submit" src="./png/migiYazirushi.png"
									class="submit_btn_hidari">
							</div>
							</form>
							<br>
							<form action="ChangeDivision">
								<div class="text_center">
									<input type="image" name="submit"
										src="./png/hidariYazirushi.png" class="submit_btn_migi">
								</div>
						</div>
					</div>
					<div class="col-5" id="column4">
						<h4 class="text_center">休業</h4>
						<%
							for (Workday_flagDTO wfd : departmentList) {
									if (wfd.isWorkflag() == false) {
						%>

						<p class="text_center">
							<input type="checkbox" name="closeDep" value="<%=wfd.getName()%>"><%=wfd.getName()%>部
						</p>
						<%
							}
								}
						%>
						</form>
					</div>
					<%
						}
					%>
				</div>
			</div>
		</div>
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