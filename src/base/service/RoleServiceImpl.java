package base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.RoleDAO;
import base.entities.Role;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;

	@Override
	public List<Role> findAll() {
		return roleDAO.findAll();
	}

	@Override
	public Role find(Integer id) {
		return roleDAO.find(id);
	}

	@Override
	public void create(Role role) {
		roleDAO.create(role);
	}

	@Override
	public void delete(Role role) {
		roleDAO.delete(role);
	}

	@Override
	public void update(Role role) {
		roleDAO.update(role);
	}

	@Override
	public Role findByName(String name) {
		return roleDAO.findByName(name);
	}

}
