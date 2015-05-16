package oops.dao.impl;

import java.util.List;

import oops.dao.EmployeeDao;
import oops.daosupport.FindByPageHibernateDaoSupport;
import oops.domain.Employee;
import oops.domain.Manager;

public class EmployeeDaoHibernate extends FindByPageHibernateDaoSupport implements EmployeeDao {

	@Override
	public Employee get(Integer id) {
		return getHibernateTemplate().get(Employee.class, id);
	}

	@Override
	public Integer save(Employee employee) {
		return (Integer)getHibernateTemplate().save(employee);
	}

	@Override
	public void update(Employee employee) {
		getHibernateTemplate().update(employee);
	}

	@Override
	public void delete(Employee employee) {
		getHibernateTemplate().delete(employee);
		
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public List<Employee> findAll() {
		return (List<Employee>)getHibernateTemplate().find("from Employee");
	}

	@Override
	public List<Employee> findByNameAndPass(Employee emp) {
		return (List<Employee>)getHibernateTemplate().find("from Employee as p where p.name =? and p.pass=?",emp.getName(),emp.getPass());
	}

	@Override
	public Employee findByName(String name) {
		List<Employee> emps = (List<Employee>)getHibernateTemplate().find("from Employee as p where p.name=?",name);
		if(emps!=null&&emps.size()>=1)
		{
			return emps.get(0);
		}
		return null;
	}

	@Override
	public List<Employee> findByMgr(Manager mgr) {
		return (List<Employee>)getHibernateTemplate().find("from Employee as p where "+"p.manager=?",mgr);
	}
	
}
