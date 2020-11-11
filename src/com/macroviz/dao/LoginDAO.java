package com.macroviz.dao;


import java.sql.*;

import com.macroviz.model.Admin;



public class LoginDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/cake_house?serverTimezone=UTC&useSSL=false";
    private String jdbcusername = "admin";
    private String jdbcPass = "admin";
  
    private static final String SELECT_ADMIN_BY_ID = "select * from admins where email =? and password=?";
  
    public LoginDAO(){}

    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcusername, jdbcPass);

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
   
    public boolean checkAdmin(Admin admin) throws ClassNotFoundException{
        
        boolean status = false;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ID);) {
            preparedStatement.setString(1, admin.getEmail());
            preparedStatement.setString(2, admin.getPassword());
            System.out.println(preparedStatement);
            ResultSet rsAdmin = preparedStatement.executeQuery();
            status = rsAdmin.next();
        }catch(SQLException e){
            printSQLException(e);
        }
        return status;
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
