package com.macroviz.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.macroviz.model.Admin;
import com.macroviz.model.User;
//import com.macroviz.dao.LoginDAO;
import com.macroviz.dao.UserDAO_example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO_example userDAO;
    //private LoginDAO loginDAO;

    public void init(){
        userDAO = new UserDAO_example();
        //loginDAO = new LoginDAO();
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
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                	listUser(request, response);
                    break;
                
                
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }
    }
    
    private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServletException {
		//使用userDAO呼叫selectAllUser
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("backend/users/list.jsp");
		dispatcher.forward(request, response);
		System.out.println("test");
	}
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("backend/users/form.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("backend/users/form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }
    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String created_at = request.getParameter("created_at");
        String updated_at = "null";
        User newUser = new User(name, email, created_at, updated_at);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");

    }
    
    private void updateUser (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String created_at = "null";
        String updated_at = request.getParameter("created_at");
        User book = new User(id, name, email, created_at, updated_at);
        userDAO.updateUser(book);
        response.sendRedirect("list");

    }
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");

    }
    
}