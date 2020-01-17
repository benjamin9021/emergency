package com.naver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cPath = request.getContextPath();
		String what = uri.substring(cPath.length());
//		System.out.println(uri);
//		System.out.println(cPath);
//		System.out.println(what);
//		HttpSession sess = request.getSession(false);
//		if (sess != null) {
//			String id = (String) sess.getAttribute("id");
//		}else {
//			System.out.println("濡쒓렇�씤 �븯�꽭�슂");
//		}
		
		Command com = null;
		if (what.equalsIgnoreCase("/insertui.do")) {
			com = new InsertUICommand();
		} else if (what.equalsIgnoreCase("/insert.do")) {
			com = new InsertCommand();
		} else if (what.equalsIgnoreCase("/list.do")) {
			com = new ListCommand();
		}else if (what.equalsIgnoreCase("/loginui.do")) {
			com = new LoginUICommand();
		}else if (what.equalsIgnoreCase("/login.do")) {
			com = new LoginCommand();
		}else if (what.equalsIgnoreCase("/logout.do")) {
			com = new LogoutCommand();
		}
		
		if (com != null) {
			com.execute(request, response);
		}

		if (com != null) {
			CommandAction action = com.execute(request, response);
			if (action.isRedirect()) {
				response.sendRedirect(action.getWhere());
			} else {
				request.getRequestDispatcher(action.getWhere()).forward(request, response);
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
