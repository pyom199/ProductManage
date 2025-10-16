package model.entity;

import java.io.Serializable;

public class CategoryBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String categoryName;

	public CategoryBean() {
	}

	public CategoryBean(int id, String categoryName) {
		this.id = id;
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
