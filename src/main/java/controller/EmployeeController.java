package controller;

import dao.EmployeeDao;
import domain.Employee;
import domain.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * POJO类(普通的Java对象作为具体的控制器)
 * @author Administrator
 */
public class EmployeeController {
	
	private EmployeeDao dao;

	public EmployeeDao getDao() {
		return dao;
	}

	public void setDao(EmployeeDao dao) {
		this.dao = dao;
	}
	
	//列表处理
	public void listService(HttpServletRequest request){
		//第一步，通过DAO对象，获取职员信息list集合对象
		List<Employee> employees=dao.findAll();
		//System.out.println("employees="+employees);
		
	    //第二步，利用request对象存储数据（集合数据）
		request.setAttribute("employees", employees);
	}
	
	//增加处理
	public void insertService(HttpServletRequest request){
		//第一步通过request对象获得表单的数据
		String name=request.getParameter("name");
		String job=request.getParameter("job");
		double salary=Double.parseDouble(request.getParameter("salary"));
		//第二步：通过DAO,将数据存入数据库表中
		Employee e=new Employee();
		e.setName(name);
		e.setJob(job);
		e.setSalary(salary);
		dao.save(e);
	}
	
	//删除处理
	public void deleteService(HttpServletRequest request){
		System.out.println("controller delete...");
		//第一步，通过request对象获得id号
		int id =Integer.parseInt(request.getParameter("id"));
		
		//第二步，通过DAO对象，删除id号指定的实体对象
		dao.delete(id);
	}
	
	//加载处理
	public void loadService(HttpServletRequest request){
		//第一步，通过request对象获得id号
		int id=Integer.parseInt(request.getParameter("id"));
		//第二步，通过dao对象，获取数据信息
		Employee e=dao.findById(id);
		
		//第三步，将职员对象存储到request对象中
		request.setAttribute("employee", e);
	}
	
	//修改处理
	public void updateService(HttpServletRequest request){
		//第一步，获得表单数据
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String job=request.getParameter("job");
		double salary=Double.parseDouble(request.getParameter("salary"));
		//第二步,通过DAO对象，更新数据
		Employee e=new Employee();
		e.setId(id);
		e.setName(name);
		e.setJob(job);
		e.setSalary(salary);
		dao.update(e);
	}
	

}
