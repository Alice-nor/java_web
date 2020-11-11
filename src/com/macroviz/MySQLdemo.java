package com.macroviz;

import java.sql.*;
import java.sql.SQLException;

public class MySQLdemo {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/cake_house?serverTimezone=UTC&useSSL=false";
    static final String USER = "admin";
    static final String PASS = "admin";
    public static void main(String[] args) {
    	Connection conn = null;
        Statement stmt = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	System.out.println("連接數據庫...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //查詢
            System.out.println(" 實例化Statement對象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                // 通過字段檢索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String mail = rs.getString("email");
    
                // 輸出數據
                System.out.print("ID: " + id);
                System.out.print(", 使用者: " + name);
                System.out.print(", 電子信箱: " + mail);
                System.out.print("\n");
            }
            // 完成后關閉
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 處理 JDBC 錯誤
            se.printStackTrace();
        }catch(Exception e){
            // 處理 Class.forName 錯誤
            e.printStackTrace();
        }finally{
            // 關閉資源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
