package base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.*;
import base.entities.*;

@Service("importService")
@Transactional
public class ImportServiceImpl implements ImportService {

	@Autowired
	private ImportDAO importDAO;
	
	@Override
	public List<Imports> findAll() {
		return importDAO.findAll();
	}

	@Override
	public Imports find(Integer id) {
		return importDAO.find(id);
	}

	@Override
	public List<Imports> findByCheckMan(String username) {
		return importDAO.findByCheckMan(username);
	}

	@Override
	public void create(Imports imports) {
		importDAO.create(imports);
	}

	@Override
	public void update(Imports imports) {
		importDAO.update(imports);
	}

}
