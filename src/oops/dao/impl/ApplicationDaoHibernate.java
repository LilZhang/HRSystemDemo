package oops.dao.impl;

import java.util.List;

import oops.dao.ApplicationDao;
import oops.daosupport.FindByPageHibernateDaoSupport;
import oops.domain.Application;
import oops.domain.Employee;

public class ApplicationDaoHibernate extends FindByPageHibernateDaoSupport implements ApplicationDao {

	@Override
	public Application get(Integer id) {
		return getHibernateTemplate().get(Application.class, id);
	}

	@Override
	public Integer save(Application application) {
		return (Integer)getHibernateTemplate().save(application);
	}

	@Override
	public void update(Application application) {
		getHibernateTemplate().update(application);
		
	}

	@Override
	public void delete(Application application) {
		getHibernateTemplate().delete(application);
		
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
		
	}

	@Override
	public List<Application> findAll() {
		return (List<Application>)getHibernateTemplate().find("from Application");
	}

	@Override
	public List<Application> findByEmp(Employee emp) {
		return (List<Application>)getHibernateTemplate().find("from Application as a where " + "a.attend.employee=?",emp);
	}
	
}
