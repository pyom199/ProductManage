package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CategoryDAO;
import model.entity.CategoryBean;

@WebServlet("/CategoryRegisterServlet")
public class CategoryRegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String idStr = request.getParameter("id");
		String name = request.getParameter("categoryName");

		String error = null;
		int id = 0;

		// 送信前のチェック 
		if (idStr == null || idStr.trim().isEmpty()) {
			error = "カテゴリIDは必須です。";
		} else {
			try {
				id = Integer.parseInt(idStr.trim());
				if (id <= 0) {
					error = "カテゴリIDは正の整数を入力してください。";
				}
			} catch (NumberFormatException e) {
				error = "カテゴリIDは数値で入力してください。";
			}
		}

		if (name == null || name.trim().isEmpty()) {
			error = "カテゴリ名は必須です。";
		}

		//DB側のID重複チェック
		CategoryDAO dao = new CategoryDAO();
		if (error == null && dao.existsById(id)) {
			error = "このカテゴリIDはすでに登録されています。";
		}

		//エラー処理
		if (error != null) {
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("/category-register.jsp");
			rd.forward(request, response);
			return;
		}

		//登録処理
		CategoryBean cb = new CategoryBean();
		cb.setId(id);
		cb.setCategoryName(name.trim());

		boolean success = dao.insert(cb);

		if (success) {
			// 登録成功 → 一覧ページにリダイレクト
			response.sendRedirect(request.getContextPath() + "/CategoryListServlet");
		} else {
			request.setAttribute("error", "カテゴリ登録に失敗しました。");

			RequestDispatcher rd = request.getRequestDispatcher("/category-register.jsp");
			rd.forward(request, response);
		}
	}
}