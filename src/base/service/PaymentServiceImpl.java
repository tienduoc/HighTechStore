package base.service;

import base.dao.*;
import base.entities.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("paymentService")
@Transactional
public class PaymentServiceImpl 
	implements PaymentService {

	@Autowired
	private PaymentDAO paymentDAO;

	@Override
	public Payment find(Integer id) {
		return paymentDAO.find(id);
	}
	
	@Override
	public List<Payment> findAll() {
		return paymentDAO.findAll();
	}

	@Override
	public void create(Payment payment) {
		paymentDAO.create(payment);
	}

	@Override
	public void delete(Payment payment) {
		paymentDAO.delete(payment);
	}

	@Override
	public void update(Payment payment) {
		paymentDAO.update(payment);
	}

	@Override
	public List<Payment> search(String keyword) {
		return paymentDAO.search(keyword);
	}

	@Override
	public Payment findByName(String name) {
		return paymentDAO.findByName(name);
	}
	

}
