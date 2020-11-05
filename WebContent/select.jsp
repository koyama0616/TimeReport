<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<title>検索</title>
</head>

<body>
	<h1>検索条件入力　　　<span class="loginInfo">ログイン者名：${ sessionScope.LOGIN_ACC.nmEmployee }</span></h1>
	<form action="select" method="post" >
	    <c:if test="${errMsg != null}">
            <div class="msgBox error">
                <c:forEach items="${ errMsg }" var="errList">
                    <c:out value="${errList}" />
                </c:forEach>
            </div>
        </c:if>
		<table>
					<tr>
				<th class="header">部署ID</th>
				<td>
					<input type="text" name=stfDepId placeholder="社員ID" value="${stfDepId }" />
				</td>
			</tr>
			<tr>
				<th class="header">社員ID</th>
				<td>
					<input type="text" name="stfId" placeholder="社員ID" value="${stfId }" />
				</td>
			</tr>
			<tr>
				<th class="header">社員名</th>
				<td>
					<input type="text" name="stfName" placeholder="社員名" value="${stfName }" />
				</td>
			</tr>
			<tr>
				<th class="header">メールアドレス</th>
				<td>
					<input type="text" name="stfMail" placeholder="メールアドレス" value="${stfMail }" />
				</td>
			</tr>
			<tr>
				<th class="header">管理者権限</th>
				<td>
					<input type="text" name="accAuth" placeholder="管理者権限" value="${stfAuth }" />
				</td>
			</tr>
			<!--<tr>
				<th class="header">所属部署</th>
				<td>
					<select name="depId">
						<option value=""> </option>
						<c:forEach items="${ sessionScope.DEP_LIST }" var="depList">
							<option value="${ depList.idDepartment }" ${depList.idDepartment == depId ? 'selected="selected"' : ''}><c:out value="${ depList.nmDepartment }" /></option>
						</c:forEach>
					</select>
				</td>
			</tr>-->
			<tr>
				<td colspan="2" class="btnArea">
					<input type="submit" value="検索" />
				</td>
			</tr>
		</table>
	</form>
	<a href="menu.jsp" >メニューに戻る</a>
</body>
</html>