package oops.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import oops.dao.AttendDao;
import oops.daosupport.FindByPageHibernateDaoSupport;
import oops.domain.Attend;
import oops.domain.AttendType;
import oops.domain.Employee;

public class AttendDaoHibernate extends FindByPageHibernateDaoSupport implements AttendDao {

	@Override
	public Attend get(Integer id) {
		return getHibernateTemplate().get(Attend.class, id);
	}

	@Override
	public Integer save(Attend attend) {
		return (Integer)getHibernateTemplate().save(attend);
	}

	@Override
	public void update(Attend attend) {
		getHibernateTemplate().update(attend);
		
	}

	@Override
	public void delete(Attend attend) {
		getHibernateTemplate().delete(attend);
		
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
		
	}

	@Override
	public List<Attend> findAll() {
		return (List<Attend>)getHibernateTemplate().find("from Attend");
	}

	@Override
	public List<Attend> findByEmp(Employee employee) {
		return (List<Attend>)getHibernateTemplate().find("from Attend as a where "+"a.employee=?",employee);
	}

	@Override
	public List<Attend> findByEmpAndDutyDay(Employee employee, String dutyDay) {
		return (List<Attend>)getHibernateTemplate().find("from Attend as a where a.employee = ? and "+"a.dutyDay=?",new Object[]{employee,dutyDay});
	}

	@Override
	public Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay,
			boolean isCome) {
		List<Attend> a1 = findByEmpAndDutyDay(emp,dutyDay);
		if(a1 !=null || a1.size()>1)
		{
			for(Attend attend:a1)
			{
				if(attend.getIsCome()==isCome)
				{
					return attend;
				}
				
			}
		}
		return null;
	}

	@Override
	public List<Attend> findByEmpUnAttend(Employee emp, AttendType type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String end = sdf.format(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -3);
		String start = sdf.format(c.getTime());
		Object[] args={emp,type,start,end};
		return (List<Attend>)getHibernateTemplate().find("from Attend as a where a,employee=? and"+"a.type != ? and a.dutyDay between ? and ?",args);
	}
	
}
