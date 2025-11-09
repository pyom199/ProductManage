package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CategoryDAO;
import model.entity.CategoryBean;

@WebServlet("/CategoryListServlet")
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// DAOを使ってカテゴリ一覧を取得
		CategoryDAO dao = new CategoryDAO();
		List<CategoryBean> categories = dao.findAll();

		// デバッグ用ログ出力
		System.out.println("取得カテゴリ数 (Servlet): " + (categories != null ? categories.size() : -1));
		if (categories != null) {
			for (CategoryBean c : categories) {
				System.out.println(c.getId() + " : " + c.getCategoryName());
			}
		}

		// JSPへ渡す
		request.setAttribute("categories", categories);

		// JSPへフォワード
		request.getRequestDispatcher("/category-list.jsp").forward(request, response);
	}
}
