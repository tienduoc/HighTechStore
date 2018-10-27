package base.dao;

import java.util.List;

import base.entities.*;

public interface LogsDAO {

	public List<Logs> findAll();
	
	public void create(Logs log);
	
	public List<Logs> search(String keyword);

	public Logs find(int id);
}
