<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<title>検索結果表示</title>
</head>

<body>
	<table>
		<thead>
			<tr>
				<th style="width: 60px;">部署ID</th>
				<th style="width: 60px;">社員ID</th>
				<th style="width: 100px;">社員名</th>
				<th style="width: 100px;">メールアドレス</th>
				<th style="width: 150px;">管理者権限</th>
				<th style="width: 60px;">パスワード</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ stfInfoList }" var="stfInfo">
				<tr>
					<td><c:out value="${ stfInfo.staff.stfDepId }" /></td>
					<td><c:out value="${ stfInfo.staff.stfId}" /></td>
					<td><c:out value="${ stfInfo.staff.stfName }" /></td>
					<td><c:out value="${ stfInfo.staff.stfMail }" /></td>
					<td><c:out value="${ stfInfo.staff.stfAuth }" /></td>
					<td><c:out value="${ stfInfo.staff.stfPass }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>
	<a href="select.jsp">検索条件入力に戻る</a>　|　<a href="menu.jsp">メニューに戻る</a>
</body>
</html>