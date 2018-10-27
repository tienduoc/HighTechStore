package base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.SupplierDAO;
import base.entities.Supplier;

@Service("supplierService")
@Transactional
public class SupplierServiceImpl implements SupplierService  {


	@Autowired
	private SupplierDAO supplierDAO;

	@Override
	public Supplier find(Integer id) {
		return supplierDAO.find(id);
	}
	
	@Override
	public List<Supplier> findAll() {
		return supplierDAO.findAll();
	}

	@Override
	public void create(Supplier supplier) {
		supplierDAO.create(supplier);
	}

	@Override
	public void delete(Supplier supplier) {
		supplierDAO.delete(supplier);
	}

	@Override
	public void update(Supplier supplier) {
		supplierDAO.update(supplier);
	}

	@Override
	public List<Supplier> search(String keyword) {
		return supplierDAO.search(keyword);
	}

	@Override
	public Supplier findByName(String name) {
		return supplierDAO.findByName(name);
	}


}
