package oops.dao.impl;

import java.util.List;

import oops.dao.PaymentDao;
import oops.daosupport.FindByPageHibernateDaoSupport;
import oops.domain.Employee;
import oops.domain.Payment;

public class PaymentDaoHibernate extends FindByPageHibernateDaoSupport implements PaymentDao {

	@Override
	public Payment get(Integer id) {
		return getHibernateTemplate().get(Payment.class, id);
	}

	@Override
	public Integer save(Payment payment) {
		return (Integer)getHibernateTemplate().save(payment);
	}

	@Override
	public void update(Payment payment) {
		getHibernateTemplate().update(payment);
	}

	@Override
	public void delete(Payment payment) {
		getHibernateTemplate().delete(payment);
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public List<Payment> findAll() {
		return (List<Payment>)getHibernateTemplate().find("from Payment");
	}

	@Override
	public List<Payment> findByEmp(Employee emp) {
		return (List<Payment>)getHibernateTemplate().find("from Payment as p where p.employee=?",emp);
	}

	@Override
	public Payment findByMonthAndEmp(String payMonth, Employee emp) {
		List<Payment> l1 = (List<Payment>)getHibernateTemplate().find("from Payment as p where p.payMonth = ? and p.employee = ? ",payMonth,emp);
		if(l1 != null && l1.size()>0)
		{
			return l1.get(0);
		}
		return null;
	}

}
