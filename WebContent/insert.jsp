<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<title>社員登録</title>
</head>

<body>
	<h1>社員登録　　　<span class="loginInfo"></span></h1>
	<form action="inputCheck" method="post" >
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
					<input type="text" name="stfDepId" placeholder="フリガナ" required="required" value="${ stfDepId }"
						maxlength="50"/>
				</td>
			</tr>
			<tr>
				<th class="header">社員ID</th>
				<td>
					<input type="text" name="stfId" readonly="readonly" class="readOnly" value="自動で設定されます" />
				</td>
			</tr>
			<tr>
				<th class="header">社員名</th>
				<td>
					<input type="text" name="stfName" placeholder="社員名" required="required" value="${ stfName }"
						maxlength="50"
					/>
				</td>
			</tr>
			<tr>
				<th class="header">メールアドレス</th>
				<td>
					<input type="text" name="stfMail" placeholder="メールアドレス" required="required" value="${ stfMail }"
						maxlength="100"
					/>
				</td>
			</tr>
			<tr>
				<th class="header">管理者権限</th>
				<td>
					<input type="text" name="stfAuth" placeholder="管理者権限" required="required" value="${ stfAuth }"
						maxlength="100"
					/>
				</td>
			</tr>
			<tr>
				<th class="header">パスワード</th>
				<td>
					<input type="password" name="stfPass" placeholder="パスワード" required="required" value="${ stfPass }"
						pattern="^(?=.*?[a-zA-Z])(?=.*?\d)[a-zA-Z\d]{3,10}$"
					/>
				</td>
			</tr>
			<!-- *** Chrome等のブラウザの場合自動でセットされる可能性があるので注意書きを外にも *** -->
			<tr>
				<th></th>
				<td><span style="font-size:8pt; font-weight:bold; color:red;">※半角英数字をそれぞれ1種類以上含む3-10文字</span></td>
			</tr>
		<!--	<tr>
				<th class="header">所属部署</th>
				<td>
					<select name="depId">
						<c:forEach items="${ sessionScope.DEP_LIST }" var="depList">
							<option value="${depList.idDepartment}" ${depList.idDepartment == depId ? 'selected="selected"' : ''}><c:out value="${ depList.nmDepartment }" /></option>
						</c:forEach>
					</select>
				</td>
			</tr> -->
			<tr>
				<td colspan="2" class="btnArea">
					<input type="submit" value="登録確認" />
				</td>
			</tr>
		</table>
	</form>
	<a href="menu.jsp" >メニューに戻る</a>
</body>
</html>