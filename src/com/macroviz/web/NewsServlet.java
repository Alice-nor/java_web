package com.macroviz.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.macroviz.dao.NewsDAO;
import com.macroviz.model.News;



//@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;

	public NewsServlet() {
		super();
	}

	public void init() {
		newsDAO = new NewsDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setLocale(Locale.TAIWAN);
		response.getWriter().append("Servlet at:").append(request.getContextPath());
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/news/new":
				showNewForm(request, response);
				break;
			case "/news/insert":
				insertNews(request, response);
				break;
			default:
				listNews(request, response);
				break;
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setLocale(Locale.TAIWAN);
		doGet(request, response);
	}

	private void listNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServletException {
		List <News> news = newsDAO.selecallUsers();
		//int totalrows = news.size();
		//request.setAttribute("totalrows", totalrows);
		request.setAttribute("News", news);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/news/list.jsp");
		dispatcher.forward(request, response);
		System.out.println("test");
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/news/form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
	String published_at = request.getParameter("published_at");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String created_at = request.getParameter("created_at");
	//String updated_at = request.getParameter("updated_at");
	News newArticle = new News(published_at, title, content, created_at, null);
	newsDAO.insertNews(newArticle);
	response.sendRedirect("list");
	}
	
}


