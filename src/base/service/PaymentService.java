package base.service;

import java.util.List;

import base.entities.*;

public interface PaymentService {

	public List<Payment> findAll();

	public Payment find(Integer id);
	
	public Payment findByName(String name);

	public void create(Payment payment);

	public void delete(Payment payment);

	public void update(Payment payment);
	
	public List<Payment> search(String keyword);
	
}
