<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<title>社員情報更新</title>
</head>

<body>
	<h1>社員情報更新　　　<span class="loginInfo"></span></h1>
	<form action="update" method="post">
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
					<input type="text" name="stfDepId" readonly="readonly" class="readOnly" value="${ stfDepId }" required="required" />
				</td>
			</tr>
			<tr>
				<th class="header">社員ID</th>
				<td>
					<input type="text" name="stfId" readonly="readonly" class="readOnly" value="${ stfId }" required="required" />
				</td>
			</tr>
			<tr>
				<th class="header">社員名</th>
				<td>
					<input type="text" name="stfName" placeholder="社員名" value="${ stfName }" required="required"
						maxlength="50"/>
				</td>
			</tr>
			<tr>
				<th class="header">管理者権限</th>
				<td>
					<input type="text" name="stfAuth" placeholder="管理者権限" value="${ stfAuth }" required="required"
						maxlength="50"
					/>
				</td>
			</tr>
			<tr>
				<th class="header">メールアドレス</th>
				<td>
					<input type="email" name="stfMail" placeholder="メールアドレス" value="${ stfMail }" required="required"
						maxlength="100"
					/>
				</td>
			</tr>
			<tr>
				<th class="header">パスワード</th>
				<td>
					<input type="password" name="stfPass" placeholder="※更新する場合は入力"
						pattern="^(?=.*?[a-zA-Z])(?=.*?\d)[a-zA-Z\d]{3,10}$"
					/>
					<input type="hidden" name="passOld" value="${ passOld }" />
				</td>
			</tr>
			<!-- *** Chrome等のブラウザの場合自動でセットされる可能性があるので注意書きを外にも *** -->
			<tr>
				<th></th>
				<td><span style="font-size:8pt; font-weight:bold; color:red;">更新する場合は入力してください<br>※半角英数字をそれぞれ1種類以上含む3-10文字</span></td>
			</tr>
			<tr>
				<th class="header">パスワード確認</th>
				<td>
					<input type="password" name="passConf" placeholder="※更新する場合は再入力"/>
				</td>
			</tr>
			<!--<tr>
				<th class="header">所属部署</th>
				<td>
					<select name="depId"  required="required">
						<c:forEach items="${ sessionScope.DEP_LIST }" var="depList">
							<option value="${depList.idDepartment}" ${depList.idDepartment == depId ? 'selected="selected"' : ''}><c:out value="${ depList.nmDepartment }"/></option>
						</c:forEach>
					</select>
				</td>
			</tr> -->
			<tr>
				<td colspan="2" class="btnArea">
					<input type="submit" value="更新" />
				</td>
			</tr>
		</table>
	</form>
	<a href="update.jsp" >更新対象の変更</a>　|　<a href="menu.jsp" >メニューに戻る</a>
</body>
</html>