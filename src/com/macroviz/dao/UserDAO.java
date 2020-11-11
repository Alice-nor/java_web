package com.macroviz.dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.macroviz.model.User;

public class UserDAO {
		
		private String jdbcURL="jdbc:mysql://127.0.0.1:3306/cake_data?serverTimezone=CST";
		private String jdbcname="admin";
		private String jdbcpassword="admin";
		private static final String SELECT_ALL_USER = "select * from users order by 'created_at' desc";
		private static final String SELECT_USER = "select * from users where id=?";
		private static final String INSERT_USER_SQL = "INSERT INTO users" + "  (name, email, created_at) VALUES " +" (?, ?, ?);";
		private static final String UPDATE_USER_SQL = "update users set name = ?,email= ? ,updated_at= ?where id = ?;";
		private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
		
		//空的建構子
		public UserDAO() {
			super();
			
		}
		
		//必須return不然會有錯誤
		protected Connection getConnection() {
			//還未連線，因此一開始都設為null
			Connection connection = null;
			
			//做連線
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL,jdbcname,jdbcpassword);
			} catch (SQLException e) {
				e.printStackTrace();
	        }catch (ClassNotFoundException e){
	            e.printStackTrace();
	        }
			return connection;
		}
		
		public List <User> selectallUsers(){
			//暫存的容器
			List<User> users = new ArrayList<>();
			//做連線
			try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER);){
				System.out.println(statement);
				ResultSet rs = statement.executeQuery();
				
				while (rs.next()) {
					int id = rs.getInt("id");
					String user_name = rs.getString("name");
					String user_email = rs.getString("email");
					String created_at = rs.getString("created_at");
					String updated_at = rs.getString("updated_at");
					//把得到的值存放在 List<User> users 容器裡面
					users.add(new User( id, user_name, user_email, created_at, updated_at ));
				}
				
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return users;
		}
		
		
		public void insertUser(User user)throws SQLException {
			try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL)){
				statement.setString(1, user.getName()); //對應欄位取得User model中的name
				statement.setString(2, user.getEmail());//對應欄位取得User model中的email
				statement.setString(3, user.getCreated_at());//對應欄位取得User model中的created_at
				statement.executeUpdate();
			} catch (SQLException e) { //測試列印sql語法
				e.printStackTrace();
			}	
		}
		
		public User selectallUsers(int id){
		User user = null;
		try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_USER);){
			statement.setInt(1, id);
			ResultSet rsUser = statement.executeQuery();
			while (rsUser.next()) {
				String user_name = rsUser.getString("name");
				String user_email = rsUser.getString("email");
				String created_at = rsUser.getString("created_at");
				String updated_at = rsUser.getString("updated_at");
				//把得到的值存放在 List<User> users 容器裡面
				user = new User( id, user_name, user_email, created_at, updated_at );
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
			
		}
		
		public void updateUser(User user)throws SQLException {
			try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);){
				statement.setString(1, user.getName()); //對應欄位取得User model中的name
				statement.setString(2, user.getEmail());//對應欄位取得User model中的email
				statement.setString(3, user.getUpdated_at());
				statement.setInt(4, user.getId());
				statement.executeUpdate();//執行sql語法
			} catch (SQLException e) { 
				e.printStackTrace();
			}	
		}

		public boolean deleteUser(int id)throws SQLException { //boolean傳回是否刪除
			boolean rowDeleted;
			try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
			return rowDeleted;
		}
}
