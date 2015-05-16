package oops.dao.impl;

import java.util.List;

import oops.dao.AttendTypeDao;
import oops.daosupport.FindByPageHibernateDaoSupport;
import oops.domain.AttendType;

public class AttendTypeDaoHibernate extends FindByPageHibernateDaoSupport implements AttendTypeDao {

	@Override
	public AttendType get(Integer id) {
		return getHibernateTemplate().get(AttendType.class, id);
	}

	@Override
	public Integer save(AttendType attendType) {
		return (Integer)getHibernateTemplate().save(attendType);
	}

	@Override
	public void update(AttendType attendType) {
		getHibernateTemplate().update(attendType);
		
	}

	@Override
	public void delete(AttendType attendType) {
		getHibernateTemplate().delete(attendType);
		
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
		
	}

	@Override
	public List<AttendType> findAll() {
		return (List<AttendType>)getHibernateTemplate().find("from AttendType");
	}

}
