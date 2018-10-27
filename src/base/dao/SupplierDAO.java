package base.dao;

import java.util.List;

import base.entities.*;

public interface SupplierDAO {

	public List<Supplier> findAll();

	public Supplier find(Integer id);

	public void create(Supplier supplier);

	public void delete(Supplier supplier);

	public void update(Supplier supplier);
	
	public List<Supplier> search(String keyword);
	
	public Supplier findByName(String username);
	

}
