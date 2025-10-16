<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entity.CategoryBean"%>
<html>
<body>
	<h2>カテゴリリスト</h2>
	<table border="1">
		<tr>
			<th>カテゴリID</th>
			<th>カテゴリ名</th>
		</tr>

		<%
		List<CategoryBean> categories = (List<CategoryBean>) request.getAttribute("categories");
		if (categories != null && !categories.isEmpty()) {
			for (CategoryBean cat : categories) {
		%>
		<tr>
			<td><%=cat.getId()%></td>
			<td><%=cat.getCategoryName()%></td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="2">データがありません</td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
