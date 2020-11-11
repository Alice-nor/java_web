package com.macroviz.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.macroviz.model.Admin;

import com.macroviz.dao.LoginDAO;


import java.io.IOException;

import java.util.Locale;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDAO loginDAO;

    public void init(){
    	loginDAO = new LoginDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setLocale(Locale.TAIWAN);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);

        try {
            if (loginDAO.checkAdmin(admin)) {
                HttpSession session = request.getSession();
                 session.setAttribute("username",email);
                response.sendRedirect("backend/users/list.jsp");
            } else {
            	response.sendRedirect("backend/login.jsp?error=true");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    	
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/login.jsp");
        dispatcher.forward(request, response);
		
    }
    
}
