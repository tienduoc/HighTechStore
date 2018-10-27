package base.service;

import java.util.List;

import base.entities.Account;

public interface AccountService {

public List<Account> findAll();
	
	public Account find(String username);
	
	public void create(Account account);
	
	public void delete(Account account);
	
	public void update(Account account);
	
	public List<Account> search(String keyword);
}
