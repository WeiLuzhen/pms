package dao;

import domain.Employee;

import java.util.List;

public interface EmployeeDao {
	//增加一条记录
	public void save(Employee e);
	//删除一条记录
	public void delete(int id);
	//修改一条记录
	public void update(Employee e);
	//唯一性查询
	public Employee findById(int id);
	//多条记录的查询
	public List<Employee> findAll();
}
