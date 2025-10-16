package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;

public class CategoryDAO {

	public List<CategoryBean> findAll() {
		List<CategoryBean> list = new ArrayList<>();

		String sql = "SELECT id, category_name FROM categories";

		try (Connection conn = ConnectionManager.getConnection()) {

			// ここで接続中のDB名を取得して表示 エラーが多発した原因特定
			try (PreparedStatement psDb = conn.prepareStatement("SELECT DATABASE()");
					ResultSet rsDb = psDb.executeQuery()) {
				if (rsDb.next()) {
					System.out.println("DAO: 接続中のDB = " + rsDb.getString(1));
				}
			}

			try (PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {

				System.out.println("DAO: SQLを実行しました。");

				while (rs.next()) {
					System.out.println("DAO: レコードを取得");
					CategoryBean cb = new CategoryBean();
					cb.setId(rs.getInt("id"));
					cb.setCategoryName(rs.getString("category_name"));
					list.add(cb);
				}

				System.out.println("DAO: 取得カテゴリ件数 = " + list.size());
			}

		} catch (Exception e) {
			System.out.println("DAO: 例外が発生しました");
			e.printStackTrace();
		}

		return list;
	}
}