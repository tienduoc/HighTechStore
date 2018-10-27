package base.dao;

import java.util.List;

import base.entities.Role;

public interface RoleDAO {

	public List<Role> findAll();
	
	public Role find(Integer id);
	
	public Role findByName(String name);
	
	public void create(Role role);
	
	public void delete(Role role);
	
	public void update(Role role);

}
