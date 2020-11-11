package com.macroviz.model;

public class News {
	protected int id;
	protected String published_at;
	protected String title;
	protected String content;
	protected String created_at;
	protected String updated_at;
	
	//建構式
	public News(int id, String published_at, String title, String content, String created_at, String updated_at) {
		super();
		this.id = id;
		this.published_at = published_at;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public News( String published_at, String title, String content, String created_at, String updated_at) {
		super();
		this.published_at = published_at;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPublished_at() {
		return published_at;
	}

	public void setPublished_at(String published_at) {
		this.published_at = published_at;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
	
	
	
	
	
	
	
	
}
