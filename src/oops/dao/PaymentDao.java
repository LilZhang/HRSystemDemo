package oops.dao;

import java.util.List;

import oops.domain.Employee;
import oops.domain.Payment;

public interface PaymentDao {
	public Payment get(Integer id);
	public Integer save(Payment payment);
	public void update(Payment payment);
	public void delete(Payment payment);
	public void delete(Integer id);
	public List<Payment> findAll();
	public List<Payment> findByEmp(Employee emp);
	public Payment findByMonthAndEmp(String payMonth,Employee emp);
}
