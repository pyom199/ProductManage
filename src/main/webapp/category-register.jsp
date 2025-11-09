<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ情報入力フォーム</title>
<script>
	function validateForm() {
		const id = document.forms["categoryForm"]["id"].value.trim();
		const name = document.forms["categoryForm"]["categoryName"].value
				.trim();

		if (id === "") {
			alert("カテゴリIDは必須です。");
			return false;
		}
		if (isNaN(id) || parseInt(id) <= 0) {
			alert("カテゴリIDは正の整数で入力してください。");
			return false;
		}
		if (name === "") {
			alert("カテゴリ名は必須です。");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<h2>カテゴリ情報入力フォーム</h2>

	<form
		action="${pageContext.request.contextPath}/CategoryRegisterServlet"
		method="post">
		<table>
			<tr>
				<td>カテゴリID：</td>
				<td><input type="text" name="id"
					value="<%=request.getParameter("id") != null ? request.getParameter("id") : ""%>"></td>
			</tr>
			<tr>
				<td>カテゴリ名：</td>
				<td><input type="text" name="categoryName"
					value="<%=request.getParameter("categoryName") != null ? request.getParameter("categoryName") : ""%>"></td>
			</tr>
		</table>
		<input type="submit" value="登録">
	</form>

	<hr>
	<%-- カテゴリ一覧へ戻るボタン --%>
	<form action="${pageContext.request.contextPath}/CategoryListServlet"
		method="get">
		<input type="submit" value="カテゴリ一覧へ戻る">
	</form>
</body>
</html>
