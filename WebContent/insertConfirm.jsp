<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<title>登録内容確認</title>
</head>

<body>
	<h1>登録内容確認　　　<span class="loginInfo"></span></h1>
<!--
********************************************
登録内容編集画面へ遷移した際、
入力値が保持されていないバグ対応
********************************************
 -->
<!-- 	<form action="insert" method="post" >	 -->
	<form action="insert" method="post" name="insertForm">

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
					<input type="text" name="stfDepId" readonly="readonly" class="readOnly" value="${ stfDepId }" />
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
					<input type="text" name="stfName" readonly="readonly" class="readOnly" value="${ stfName }" />
				</td>
			</tr>
			<tr>
				<th class="header">管理者権限</th>
				<td>
					<input type="text" name="stfAuth" readonly="readonly" class="readOnly" value="${ stfAuth }" />
				</td>
			</tr>
			<tr>
				<th class="header">パスワード確認</th>
				<td>
					<input type="hidden" name="stfPass" value="${ stfPass }" />
					<input type="password" name="passConfirm" placeholder="再入力してください" />
				</td>
			</tr>
			<tr>
				<th class="header">所属部署</th>
				<td>
					<select name="depId" class="readOnly">
						<c:forEach items="${ sessionScope.DEP_LIST }" var="depList">
							<option  class="readOnly" value="${depList.Id}" ${depList.Id == Id ? 'selected="selected"' : 'disabled="disabled"'}><c:out value="${ depList.Department_id }" /></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="btnArea">
					<input type="submit" value="登録" />
				</td>
			</tr>
		</table>

 <!--
	</form>
	<a href="insert.jsp" >登録内容の編集</a>　|　<a href="menu.jsp" >メニューに戻る</a>
 -->
 		<a href="javascript:onClickEdit()" >登録内容の編集</a>　|　<a href="menu.jsp" >メニューに戻る</a>
 		<input id="editFlg" name="editFlg" type="hidden" value="insert">
 	</form>


 	<script type="text/javascript">
 		function onClickEdit() {
 			var editFlg = document.getElementById('editFlg');
 			editFlg.value = 'edit';
			document.insertForm.submit();
 		}
 	</script>

</body>
</html>