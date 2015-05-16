package oops.dao.impl;

import java.util.List;

import oops.dao.ManagerDao;
import oops.daosupport.FindByPageHibernateDaoSupport;
import oops.domain.Manager;

public class ManagerDaoHibernate extends FindByPageHibernateDaoSupport implements ManagerDao {

	@Override
	public Manager get(Integer id) {
		return getHibernateTemplate().get(Manager.class, id);
	}

	@Override
	public String save(Manager manager) {
		return (String)getHibernateTemplate().save(manager);
	}

	@Override
	public void update(Manager manager) {
		getHibernateTemplate().update(manager);
		
	}

	@Override
	public void delete(Manager manager) {
		getHibernateTemplate().delete(manager);
		
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public List<Manager> findAll() {
		return (List<Manager>)getHibernateTemplate().find("from Manager");
	}

	@Override
	public List<Manager> findByNameAndPass(Manager manager) {
		return (List<Manager>)getHibernateTemplate().find("from Manager m where m.name=? and m.pass=?",manager.getName(),manager.getPass());
	}

	@Override
	public Manager findByName(String name) {
		List<Manager> l1 = (List<Manager>)getHibernateTemplate().find("from Manager m where m.name=?",name);
		if(l1!=null&&l1.size()>0)
		{
			return l1.get(0);
		}
		return null;
	}

}
