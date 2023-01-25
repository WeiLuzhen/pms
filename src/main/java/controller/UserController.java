package controller;

import javax.servlet.http.HttpServletRequest;

import dao.UserDao;
import domain.User;

public class UserController {
	private UserDao dao;

	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	public boolean login(HttpServletRequest request){
		String errorMessage="";
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("username="+username);
		System.out.println("password="+password);
		User validUser=dao.findByNickname(username);
		
		if(validUser==null||!validUser.getPassword().equals(password)){
			errorMessage="用户名或者密码错误,请重新登录!";
			request.setAttribute("errorMessage", errorMessage);
			return false;
		}
		request.getSession().setAttribute("validUser", validUser);
		return true;
	}
	
	
}
