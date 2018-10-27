package base.dao;

import base.entities.UserRole;

public interface UserRoleDAO {

	public void create(UserRole userRole);
	
	public void update(UserRole userRole);
	
	public void delete(UserRole userRole);
	
	public UserRole find(String username);
	

}
