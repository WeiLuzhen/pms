package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ComponentFactory;
import dao.EmployeeDao;
import dao.UserDao;

public class ActionServlet extends HttpServlet {

	//Servlet的请求处理
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//--------------------------------------------------
		//第一部分:预备阶段
		
		String servletPath=request.getServletPath();
		System.out.println(servletPath);
		System.out.println(servletPath.indexOf("/employee"));
		
		//请求路径中不仅要包含模块的标识关键字(employee或者user),
		// 且标识关键字必须出现在开始的位置
		if((!servletPath.startsWith("/employee/"))&&(!servletPath.startsWith("/user/"))){
			response.sendError(404);
			return;
		}
		
		//准备request对象及请求路径的解析
		request.setCharacterEncoding("utf-8");
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));

		//创建组件对象
        EmployeeController empController=new EmployeeController();	
        EmployeeDao empDao=(EmployeeDao)ComponentFactory.getInstance("EmployeeDao");
        empController.setDao(empDao);
        
        UserController userController=new UserController();
        UserDao userDao=(UserDao)ComponentFactory.getInstance("UserDao");
        userController.setDao(userDao);
        
		//--------------------------------------------------
		//第二部分:请求的分发处理
		String target="";//目标组件
		String jumpway="";//跳转方式
		//处理具体的请求
		//////////////////////////////////////////////////////////////
		if(path.equals("/UserForm")){//显示登录表单
			jumpway="forward";
			target="/WEB-INF/UserForm.jsp";
		}else if(path.equals("/UserLogin")){//登录处理
			boolean success=userController.login(request);
			System.out.println("success="+success);
			if(success){
				jumpway="redirect";
				target=request.getContextPath()+"/employee/ListEmp.do";
			}else{
				jumpway="forward";
				target="/WEB-INF/UserForm.jsp";
			}
		}
		
		////////////////////////////////////////////////////////////////
		if(path.equals("/ListEmp")){//职员列表请求的处理
			//获取职员列表，并将其存储到request对象中
			empController.listService(request);
			jumpway="forward";//转发
			target="/WEB-INF/ListEmp.jsp";
		}else if(path.equals("/InsertEmp")){//添加职员信息的请求处理
			//添加职员信息
			empController.insertService(request);
			jumpway="redirect";//重定向
			target="ListEmp.do";
		}else if(path.equals("/DeleteEmp")){//删除
			//删除职员信息
			empController.deleteService(request);
			jumpway="redirect";//重定向
			target="ListEmp.do";
		}else if(path.equals("/LoadEmp")){//查询并加载
			//加载职员信息，并存储到request对象中去
			empController.loadService(request);
			jumpway="forward";//转发
		    target="/WEB-INF/LoadEmp.jsp";
		}else if(path.equals("/UpdateEmp")){//修改
			//更新职员信息
			empController.updateService(request);
			jumpway="redirect";//重定向
			target="ListEmp.do";
		}else if(path.equals("/AddForm")){//调用添加表单
			jumpway="forward";
			target="/WEB-INF/AddEmp.jsp";
		}
		
        //------------------------------------------------------
		//第三部分:实施跳转
		jump(request,response,jumpway,target);
	}//service方法的结束符
	
	/**
	 * 负责Servlet的跳转
	 * @param request-用来获得请求转发器
	 * @param response-用来重定向
	 * @param target-目标地址
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void jump(HttpServletRequest request, HttpServletResponse response, 
			          String way,String target) throws ServletException, IOException {
		//实施转发跳转
		if("forward".equals(way)){
			RequestDispatcher dispatcher=request.getRequestDispatcher(target);
			dispatcher.forward(request, response);
		}
		//实施重定向跳转
		if("redirect".equals(way)){
			response.sendRedirect(target);
		}
	}

}
