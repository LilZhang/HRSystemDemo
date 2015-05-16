package oops.dao.impl;

import java.util.List;

import oops.dao.CheckBackDao;
import oops.daosupport.FindByPageHibernateDaoSupport;
import oops.domain.CheckBack;

public class CheckBackDaoHibernate extends FindByPageHibernateDaoSupport implements CheckBackDao {

	@Override
	public CheckBack get(Integer id) {
		return getHibernateTemplate().get(CheckBack.class, id);
	}

	@Override
	public Integer save(CheckBack checkBack) {
		return (Integer)getHibernateTemplate().save(checkBack);
	}

	@Override
	public void update(CheckBack checkBack) {
		getHibernateTemplate().update(checkBack);
		
	}

	@Override
	public void delete(CheckBack checkBack) {
		getHibernateTemplate().delete(checkBack);
		
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
		
	}

	@Override
	public List<CheckBack> findAll() {
		return (List<CheckBack>)getHibernateTemplate().find("from CheckBack");
	}

}
