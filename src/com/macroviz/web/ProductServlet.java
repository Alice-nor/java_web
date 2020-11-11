package com.macroviz.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.macroviz.model.Product;
import com.macroviz.dao.ProductDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    public void init(){
        productDAO = new ProductDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setLocale(Locale.TAIWAN);
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setLocale(Locale.TAIWAN);
        response.getWriter().append("Served at: ").append(request.getContextPath());
        String action = request.getServletPath();
        try{
            switch(action){
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertProduct(request, response);
                    break;
                case "/delete":
                    deleteProduct(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateProduct(request, response);
                    break;
                default:
                    listProduct(request, response);
                    break;
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }
    }
    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List < Product > listProduct = productDAO.selectAllProducts();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("backend/users/user-list.jsp");
        dispatcher.forward(request, response);
        System.out.println("test");
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("backend/users/user-form.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productDAO.selectProduct(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("backend/users/user-form.jsp");
        request.setAttribute("user", existingProduct);
        dispatcher.forward(request, response);

    }
    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
    	String picture = request.getParameter("picture");
    	String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String created_at = request.getParameter("created_at");
        String updated_at = "null";
        Product newProduct = new Product(picture, name, price, description, created_at, updated_at);
        productDAO.insertProduct(newProduct);
        response.sendRedirect("list");

    }
    private void updateProduct (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        String picture = request.getParameter("picture");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String created_at = "null";
        String updated_at = request.getParameter("created_at");
        Product book = new Product(id, picture, name, price, description, created_at, updated_at);
        productDAO.updateProduct(book);
        response.sendRedirect("list");

    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);
        response.sendRedirect("list");

    }
}
