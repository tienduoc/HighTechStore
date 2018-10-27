package base.dao;

import java.util.List;

import base.entities.*;

public interface ImportDetailDAO {

	public List<ImportDetail> findAll();
	
	public List<ImportDetail> find(int id);
	
	public List<ImportDetail> findByCheckMan(String username);
	
	public void create(ImportDetail importDetail);

}
