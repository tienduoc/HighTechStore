package base.service;

import java.util.List;

import base.entities.Supplier;

public interface SupplierService {

	public List<Supplier> findAll();

	public Supplier find(Integer id);

	public void create(Supplier supplier);

	public void delete(Supplier supplier);

	public void update(Supplier supplier);
	
	public Supplier findByName(String name);
	
	public List<Supplier> search(String keyword);

}
