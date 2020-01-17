package com.naver;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO2 dao = new MemberDAO2();
		List<MemberDTO> list = dao.list();
		request.setAttribute("list", list);
//		request.getRequestDispatcher("list.jsp").forward(request, response);
		
		return new CommandAction(false, "list.jsp");
		
	}
}
