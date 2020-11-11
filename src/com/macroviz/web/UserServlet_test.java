package com.macroviz.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import com.macroviz.dao.UserDAO;
import com.macroviz.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/User") // 路徑，找到servlet
public class UserServlet_test extends HttpServlet {
	// 下面為固定的
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public void init() {
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 顯示目前路徑，讀取路徑
		request.setCharacterEncoding("UTF-8");// 編碼使用UTF-8
		response.setLocale(Locale.TAIWAN);// 設定語系為TAIWAN
		response.getWriter().append("Servlet at: ").append(request.getContextPath());
		String action = request.getServletPath();// 取得網址後路徑
		// 判斷網址為何，決定要執行哪支程式
		try {
			switch (action) {
			case "/user/new": //顯示表單畫面
				showNewForm(request, response);
				break;
			case "/user/insert": 
				insertUser(request, response);
				break;
			case "/user/edit": 
				showEditForm(request, response);
				break;
			case "/user/update": 
				updateUser(request, response);
				break;
			case "/user/delete": 
				deleteUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

	// 有表格時 doPost出動，沒有表格則由doGet出動
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setLocale(Locale.TAIWAN);
		doGet(request, response);
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServletException {
		// 使用userDAO呼叫selectAllUser
		List<User> listUser = userDAO.selectallUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/users/list.jsp");
		dispatcher.forward(request, response);
		System.out.println("test");
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/users/form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id")); //取得list.jsp中編輯的參數id
		User existingUser = userDAO.selectallUsers(id); //從dao取得資料
		request.setAttribute("one_user", existingUser); //取得的資料變成one_user
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/users/form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String name = request.getParameter("name");//取得input name欄位的資料
		String email = request.getParameter("email");//取得input email欄位的資料
		String created_at = request.getParameter("created_at");//取得input created_at欄位的資料
		String updated_at = "null"; //因為是新增，所以更新時間設為null
		User newUser = new User(name, email, created_at, updated_at);//建立新的user資料（這裡呼叫的是user的建構子）
		userDAO.insertUser(newUser);//執行UserDAO的insertUser函式
		response.sendRedirect("list");//新增完跳回列表頁	
	}
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String created_at = "null";
		String updated_at = request.getParameter("updated_at");
		User editUser = new User(id,name, email, created_at, updated_at);
		userDAO.updateUser(editUser);
		response.sendRedirect("list");//新增完跳回列表頁	
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");
	}
}
