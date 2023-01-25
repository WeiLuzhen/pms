package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeFilter implements Filter{
	
	private static final String LOGINPATH="/user/UserForm.do";
	private static final String BACKPATH="/employee/ListEmp.do";//返回路径

	public void init(FilterConfig config) throws ServletException {
		System.out.println("EmployeeFilter#init方法在执行...");
		
	}

	public void doFilter(ServletRequest req, ServletResponse rep,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("EmployeeFilter#doFilter方法在执行...");
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)rep;
		
		String contextPath=request.getContextPath();
		System.out.println("contextPath="+contextPath );
		String servletPath=request.getServletPath();
		System.out.println("servletPath="+servletPath);
		//放行1:(列表请求放行)
		if(servletPath.startsWith(BACKPATH)){
			chain.doFilter(req, rep);
			return;
		}
		//放行2:(表明用户登录后,放行)
		if(request.getSession().getAttribute("validUser") != null) {
			chain.doFilter(req, rep);
			return;
		}
		
		//拦截处理:
		//拦截时记录一下返回地址(可以更精细的设置,这里统一回到列表页面)
		//request.getSession().setAttribute("backpath",BACKPATH);
		//(2)重定向到登录页面
		response.sendRedirect(contextPath+LOGINPATH);
		return;
	}
	
	public void destroy() {}

}
