package com.naver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.sendRedirect("insert.jsp");
//		request.getRequestDispatcher("insert.jsp").forward(request, response);
		return new CommandAction(true, "insert.jsp");
	}

}
