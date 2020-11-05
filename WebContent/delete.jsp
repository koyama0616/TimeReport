<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<title>社員情報削除</title>
</head>

<body>
	<h1>社員情報削除　　　<span class="loginInfo">ログイン者名：${ sessionScope.LOGIN_STF.stfId }</span></h1>
	<form action="targetSearch" method="post">
	    <c:if test="${errMsg != null}">
            <div class="msgBox error">
                <c:forEach items="${ errMsg }" var="errList">
                    <c:out value="${errList}" />
                </c:forEach>
            </div>
        </c:if>
        <table>
            <tr>
                <th class="header">社員ID</th>
                <td style="width: 250px;">
                    <input type="text" placeholder="社員ID" name="stfId" required="required"
                        style="width: 100%;" value="${stfId }" />
                </td>
            </tr>
            <tr>
                <td colspan="2" class="btnArea">
                	<input type="hidden" name="page" value="delete" />
                    <input type="submit" value="削除確認"  />
                </td>
            </tr>
        </table>
	</form>
	<!--<a href="menu.jsp" >メニューに戻る</a>-->
</body>
</html>