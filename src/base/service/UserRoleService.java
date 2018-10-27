package base.service;

import base.entities.UserRole;

public interface UserRoleService {

	public void create(UserRole userRole);

	public void update(UserRole userRole);
	
	public UserRole find(String username);
	
	public void delete(UserRole userRole);
	
}
