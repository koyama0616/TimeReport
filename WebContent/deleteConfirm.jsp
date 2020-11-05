<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<title>登録内容確認</title>
</head>
<html>
<body>
	<form action="delete" method="post" >
		<table>
					<tr>
				<th class="header">部署ID</th>
				<td>
					<input type="text" name="stfDepId" readonly="readonly" class="readOnly" value="${ stfDepId }" />
				</td>
			</tr>
			<tr>
				<th class="header">社員ID</th>
				<td>
					<input type="text" name="stfId" readonly="readonly" class="readOnly" value="${ stfId }" />
				</td>
			</tr>
			<tr>
				<th class="header">社員名</th>
				<td>
					<input type="text" name="stfName" readonly="readonly" class="readOnly" value="${ stfName }"/>
				</td>
			</tr>
			<tr>
				<th class="header">メールアドレス</th>
				<td>
					<input type="text" name="stfMail" readonly="readonly" class="readOnly" value="${ stfMail }" />
				</td>
			</tr>
			<tr>
				<th class="header">管理者権限</th>
				<td>
					<input type="text" name="stfAuth" readonly="readonly" class="readOnly" value="${ stfAuth }" />
				</td>
			</tr>
		<!--  	<tr>
				<th class="header">所属部署</th>
				<td>
					<select name="depId">
						<c:forEach items="${ sessionScope.DEP_LIST }" var="depList">
							<option value="${depList.idDepartment}" ${depList.idDepartment == depId ? 'selected="selected"' : 'disabled="disabled"'}>
							<c:out value="${ depList.nmDepartment }" /></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>-->
				<td colspan="2" class="btnArea">
					<input type="submit" value="削除" />
				</td>
			</tr>
		</table>
	</form>
	<a href="delete.jsp" >削除対象の変更</a>　|　<a href="menu.jsp" >メニューに戻る</a>
</body>
</html>