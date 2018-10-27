package base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.UserRoleDAO;
import base.entities.UserRole;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDAO userRoleDAO;

	@Override
	public void create(UserRole userRole) {
		userRoleDAO.create(userRole);
	}

	@Override
	public void update(UserRole userRole) {
		userRoleDAO.update(userRole);

	}

	@Override
	public UserRole find(String username) {
		return userRoleDAO.find(username);
	}

	@Override
	public void delete(UserRole userRole) {
		userRoleDAO.delete(userRole);		
	}

}
