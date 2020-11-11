package com.macroviz.dao;
//DAO為view，與servlet交換資料 呈現給使用者的畫面
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.macroviz.model.News;
import com.macroviz.model.User;


public class NewsDAO {

	
	private String jdbcURL="jdbc:mysql://127.0.0.1:3306/cake_data?serverTimezone=CST";
	private String jdbcUser="admin";
	private String jdbcPassword="admin";
	private static final String SELECT_ALL_USER = "select * from news order by 'created_at' desc";
	private static final String INSERT_NEWS_SQL = "INSERT INTO news" + "  (published_at, title, content, created_at, updated_at) VALUES " +" (?, ?, ?, ?, ?);";
	private static final String UPDATE_NEWS_SQL = "update news set published = ?,title = ? ,content = ? updated_at = ? where id = ?;";
	
	//空的建構子
	public NewsDAO() {
		super();
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,jdbcUser,jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public List <News> selecallUsers(){
		//做暫存的容器
		List <News> news = new ArrayList<>();
		//做連線
		try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER);) {
			System.out.println(statement);
			ResultSet rs = statement.executeQuery();
			
		while (rs.next()) {
			int id = rs.getInt("id");
			String published_at = rs.getString("published_at");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String created_at = rs.getString("created_at");
			String updated_at = rs.getString("updated_at");
			//把得到的值放在容器裡面
			news.add(new News(id,published_at,title,content,created_at,updated_at));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return news;
	}
	
	public void insertNews(News news)throws SQLException {
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_NEWS_SQL)){
			statement.setString(1, news.getPublished_at()); 
			statement.setString(2, news.getTitle());
			statement.setString(3, news.getContent());
			statement.setString(4, news.getCreated_at());
			statement.setString(5, news.getUpdated_at());
			statement.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		}	
	}
	
	public void updateNews(News news)throws SQLException {
		try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_NEWS_SQL);){
			statement.setString(1, news.getPublished_at()); //對應欄位取得User model中的name
			statement.setString(2, news.getContent());//對應欄位取得User model中的email
			statement.setString(3, news.getUpdated_at());
			statement.executeUpdate();//執行sql語法
		} catch (SQLException e) { 
			e.printStackTrace();
		}	
	}
	
	
	
}
