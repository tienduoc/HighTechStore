package base.service;

import java.util.List;

import base.entities.Logs;

public interface LogsService {

	public List<Logs> findAll();

	public void create(Logs log);

	public List<Logs> search(String keyword);
	
	public Logs find(int id);
}
