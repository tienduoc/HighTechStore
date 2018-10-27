package base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.*;
import base.entities.*;

@Service("logsService")
@Transactional
public class LogsServiceImpl implements LogsService {

	@Autowired
	private LogsDAO logsDAO;

	@Override
	public List<Logs> findAll() {
		return logsDAO.findAll();
	}

	@Override
	public void create(Logs logs) {
		logsDAO.create(logs);
	}

	@Override
	public List<Logs> search(String keyword) {
		return logsDAO.search(keyword);
	}

	@Override
	public Logs find(int id) {
		return logsDAO.find(id);
	}
}
