package base.dao;

import java.util.List;

import base.entities.*;

public interface ImportDAO {

	public List<Imports> findAll();
	
	public Imports find(Integer id);
	
	public List<Imports> findByCheckMan(String username);
	
	public void create(Imports imports);
	
	public void update(Imports imports);

}
