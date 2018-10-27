package base.dao;

import java.util.*;

import base.entities.*;

public interface AccountDAO {
	
	public List<Account> findAll();
	
	public Account find(String username);
	
	public void create(Account account);
	
	public void delete(Account account);
	
	public void update(Account account);	
	
	public List<Account> search(String keyword);

}

