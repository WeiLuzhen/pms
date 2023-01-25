package dao;

import domain.User;

public interface UserDao {
	
	//按照别名查找用户
	public User findByNickname(String name);
}
