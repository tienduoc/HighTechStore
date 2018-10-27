package base.dao;

import java.util.List;

import base.entities.*;

public interface PaymentDAO {

	public Payment find(Integer id);
	
	public Payment findByName(String name);

	public List<Payment> findAll();

	public List<Payment> search(String keyword);
	
	public void create(Payment payment);
	
	public void delete(Payment payment);
	
	public void update(Payment payment);

}
