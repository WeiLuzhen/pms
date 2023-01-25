package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;
import util.DBUtil;
import domain.User;

public class UserDaoImpl implements UserDao {

	public User findByNickname(String name) {
		String sql="select * from safeuser where nickname=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		User user=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			System.out.println("rs="+rs);
			if(rs.next()){
				user=new User();
				user.setId(rs.getInt("id"));
				user.setNickname(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println("异常发生了..."+e.getMessage());
			e.printStackTrace();
		} finally{
			DBUtil.close(rs, pstmt, conn);
		}
	
		return user;
	}

}
