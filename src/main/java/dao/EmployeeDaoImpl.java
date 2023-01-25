package dao;

import domain.Employee;
import domain.Employee;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl 
             implements EmployeeDao{
	
	public void save(Employee e){
			String sql="insert into emp(name,job,salary) "+
					"values(?,?,?)";
			Connection conn=DBUtil.getConnection();
			PreparedStatement pstmt=null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, e.getName());
				pstmt.setString(2, e.getJob());
				pstmt.setDouble(3, e.getSalary());
				pstmt.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally{
				DBUtil.close(pstmt, conn);
			}
	}
	public void delete(int id){
		System.out.println("test...");
		String sql="delete from emp where id=?";
		Connection conn=DBUtil.getConnection();

		PreparedStatement pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.close(pstmt, conn);
		}
	}
	public void update(Employee e){
		String sql="update emp "+
				"set name=?, job=?, salary=? "+
				"where id=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, e.getName());
			pstmt.setString(2, e.getJob());
			pstmt.setDouble(3, e.getSalary());
			pstmt.setInt(4, e.getId());
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			DBUtil.close(pstmt, conn);
		}
	}
	public Employee findById(int id){
		String sql="select * from emp where id=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Employee e=null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			//执行查询，返回单条记录的结果集对象
			rs=pstmt.executeQuery();
			System.out.println("rs="+rs);
			if(rs.next()){
				e=new Employee();
				e.setId(id);
				e.setName(rs.getString("name"));
				e.setJob(rs.getString("job"));
				e.setSalary(rs.getDouble("salary"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return e;
	}
	public List<Employee> findAll(){
		String sql="select * from emp";
		Connection conn=DBUtil.getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		List<Employee> list=
				new ArrayList<Employee>();
		try {
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				Employee e=new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setJob(rs.getString("job"));
				e.setSalary(rs.getDouble("salary"));
				list.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			DBUtil.close(rs, stmt, conn);
		}
		return list;
	}
}
