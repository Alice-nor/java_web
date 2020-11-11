package com.macroviz.dao;
import com.macroviz.model.User;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserDAO_example {
    private String jdbcURL = "jdbc:mysql://localhost:3306/cake_house?serverTimezone=UTC&useSSL=false";
    private String jdbcUsername = "admin";
    private String jdbcPass = "admin";
    private static final String INSERT_USER_SQL = "INSERT INTO users" + "  (name, email, created_at) VALUES " +
            " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select * from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users order by `created_at` desc";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ? ,updated_at= ?where id = ?;";

    public UserDAO_example(){}

    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPass);

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USER_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

        	preparedStatement.setString(1, user.getName());//對應欄位取得user model中的name
            preparedStatement.setString(2, user.getEmail());//對應欄位取得user model中的email
            preparedStatement.setString(3, user.getCreated_at());//對應欄位取得user model中的created_at
            System.out.println(preparedStatement);//執行指定的sql語法
            preparedStatement.executeUpdate();//執行insert的sql語法
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public User selectUser(int id){
        User user = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rsUser = preparedStatement.executeQuery();
            while (rsUser.next()) {
                String name = rsUser.getString("name"); 
                String email = rsUser.getString("email");
                String created_at = rsUser.getString("created_at");
                String updated_at = rsUser.getString("updated_at");
                System.out.println(created_at);
                user = new User(id, name, email,created_at,updated_at);

            }
        }catch(SQLException e){
            printSQLException(e);
        }
        return user;
    }
    public List< User > selectAllUsers(){ //取得所有user資料函式
    	//宣告一個list物件（存放user）
    	List< User > users = new ArrayList<>();
        //執行連結與取得資料
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();//執行指定的sql語法
            //跑迴圈取的資料庫的所有資料
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String created_at = rs.getString("created_at");
                String updated_at = rs.getString("updated_at");
                users.add(new User(id, name, email,created_at,updated_at));
            }

        }catch(SQLException e){ //例外處理
            printSQLException(e);
        }
        return users;
    }
    public boolean deleteUser(int id) throws SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public boolean updateUser(User user) throws SQLException{
        boolean rowUpdated;
        try( Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUpdated_at());
            statement.setInt(4, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
        private void printSQLException(SQLException ex) {
            for (Throwable e: ex) {
                if (e instanceof SQLException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
}
