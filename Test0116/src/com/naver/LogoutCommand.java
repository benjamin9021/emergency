package com.naver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		if (sess != null) {
			sess.invalidate();
//		or	sess.removeAttribute("login");
		}
		return new CommandAction(true, "list.do");
	}

}
