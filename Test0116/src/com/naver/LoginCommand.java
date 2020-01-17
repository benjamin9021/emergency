package com.naver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String sAge = request.getParameter("age");
		int age = -1;
		if (sAge != null) {
			age = Integer.parseInt(sAge);
		}
		
		MemberDAO2 dao = new MemberDAO2();
		MemberDTO login = dao.login(id,age);
				
		HttpSession sess = request.getSession();
		sess.setAttribute("login", login);

		return new CommandAction(true, "list.do");
	}

}
