package com.macroviz.dao;
import com.macroviz.model.Product;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/cake_house?serverTimezone=UTC&useSSL=false";
    private String jdbcProductname = "admin";
    private String jdbcPass = "admin";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products" + "  (name, price, description, created_at) VALUES " +
            " (?, ?, ?);";

    private static final String SELECT_PRODUCT_BY_ID = "select * from products where id =?";
    private static final String SELECT_ALL_PRODUCTS = "select * from products order by `created_at` desc";
    private static final String DELETE_PRODUCTS_SQL = "delete from products where id = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "update products set picture = ?,name = ?,price= ? ,description= ? ,`updated_at`= ? where id = ?;";

    public ProductDAO(){}

    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcProductname, jdbcPass);

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
    public void insertProduct(Product product) throws SQLException {
        System.out.println(INSERT_PRODUCT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
        	preparedStatement.setString(1, product.getPicture());
        	preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getPrice());
            preparedStatement.setString(4, product.getCreated_at());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public Product selectProduct(int id){
        Product product = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rsProduct = preparedStatement.executeQuery();
            while (rsProduct.next()) {
            	String picture = rsProduct.getString("picture");
                String name = rsProduct.getString("name");
                String price = rsProduct.getString("price");
                String description = rsProduct.getString("description");
                String created_at = rsProduct.getString("created_at");
                String updated_at = rsProduct.getString("updated_at");
                System.out.println(created_at);
                product = new Product(id, picture, name, price, description, created_at,updated_at);

            }
        }catch(SQLException e){
            printSQLException(e);
        }
        return product;
    }
    public List< Product > selectAllProducts(){
        List< Product > products = new ArrayList<>();

        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);){
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String picture = rs.getString("picture");
                String name = rs.getString("name");
                String price = rs.getString("price");
                String description = rs.getString("description");
                String created_at = rs.getString("created_at");
                String updated_at = rs.getString("updated_at");
                products.add(new Product(id, picture, name, price, description, created_at,updated_at));
            }

        }catch(SQLException e){
            printSQLException(e);
        }
        return products;
    }
    public boolean deleteProduct(int id) throws SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);){
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public boolean updateProduct(Product product) throws SQLException{
        boolean rowUpdated;
        try( Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);){
            statement.setString(1, product.getName());
            statement.setString(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setString(4, product.getUpdated_at());
            statement.setInt(5, product.getId());
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
