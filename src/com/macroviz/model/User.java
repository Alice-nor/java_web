package com.macroviz.model;

public class User {

	//資料型態需對照mysql設置的，varchar使用String
	protected int id;
	protected String name;
	protected String email;
	protected String created_at;
	protected String updated_at;
	
	//建構式 有id的版本
	public User(int id, String name, String email, String created_at, String updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	//建構式 沒有id的版本
	public User(String name, String email, String created_at, String updated_at) {
		super();
		this.name = name;
		this.email = email;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
