package base.service;

import java.util.List;

import base.entities.Role;

public interface RoleService {

public List<Role> findAll();
	
	public Role find(Integer id);
	
	public void create(Role role);
	
	public void delete(Role role);
	
	public void update(Role role);
	
	public Role findByName(String name);
}
