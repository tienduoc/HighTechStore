package base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.ImportDetailDAO;
import base.entities.ImportDetail;

@Service("importDetailService")
@Transactional
public class ImportDetailServiceImpl implements ImportDetailService {

	@Autowired
	private ImportDetailDAO importDetailDAO;
	
	@Override
	public List<ImportDetail> findAll() {
		return importDetailDAO.findAll();
	}

	@Override
	public List<ImportDetail> find(int id) {
		return importDetailDAO.find(id);
	}

	@Override
	public List<ImportDetail> findByCheckMan(String username) {
		return importDetailDAO.findByCheckMan(username);
	}

	@Override
	public void create(ImportDetail importDetail) {
		importDetailDAO.create(importDetail);
	}

}
