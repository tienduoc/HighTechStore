package base.service;

import java.util.List;

import base.entities.ImportDetail;

public interface ImportDetailService {

	public List<ImportDetail> findAll();
	
	public List<ImportDetail> find(int id);
	
	public List<ImportDetail> findByCheckMan(String username);
	
	public void create(ImportDetail importDetail);
}
