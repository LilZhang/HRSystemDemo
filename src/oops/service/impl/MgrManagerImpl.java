package oops.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import oops.dao.ApplicationDao;
import oops.dao.AttendDao;
import oops.dao.AttendTypeDao;
import oops.dao.CheckBackDao;
import oops.dao.EmployeeDao;
import oops.dao.ManagerDao;
import oops.dao.PaymentDao;
import oops.domain.Application;
import oops.domain.Attend;
import oops.domain.CheckBack;
import oops.domain.Employee;
import oops.domain.Manager;
import oops.domain.Payment;
import oops.exception.HrException;
import oops.service.MgrManager;
import oops.vo.AppBean;
import oops.vo.EmpBean;
import oops.vo.SalaryBean;

public class MgrManagerImpl implements MgrManager {
	private ApplicationDao appDao;
	private AttendDao attendDao;
	private AttendTypeDao typeDao;
	private CheckBackDao checkDao;
	private EmployeeDao empDao;
	private ManagerDao mgrDao;
	private PaymentDao payDao;
	

	public void setAppDao(ApplicationDao appDao) {
		this.appDao = appDao;
	}

	public void setAttendDao(AttendDao attendDao) {
		this.attendDao = attendDao;
	}

	public void setTypeDao(AttendTypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public void setCheckDao(CheckBackDao checkDao) {
		this.checkDao = checkDao;
	}

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}

	public void setMgrDao(ManagerDao mgrDao) {
		this.mgrDao = mgrDao;
	}

	public void setPayDao(PaymentDao payDao) {
		this.payDao = payDao;
	}

	@Override
	public void addEmp(Employee emp, String mgr) throws HrException {
		Manager m = mgrDao.findByName(mgr);
		if(m==null)
		{
			throw new HrException("新增员工时发生业务异常");
		}
	}

	@Override
	public List<SalaryBean> getSalaryByMgr(String mgr) throws HrException {
		Manager m = mgrDao.findByName(mgr);
		if(m==null)
		{
			throw new HrException("经理？/未登录？");
		}
		List<Employee> emps = empDao.findByMgr(m);
		if(emps==null || emps.size()<1){
			throw new HrException("该部门没有员工");
		}
		Calendar c =Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String payMonth =sdf.format(c.getTime());
		List<SalaryBean> result = new ArrayList<SalaryBean>();
		for(Employee e:emps)
		{
			Payment p = payDao.findByMonthAndEmp(payMonth, e);
			if(p != null)
			{
				result.add(new SalaryBean(e.getName(),p.getAmount()));
			}
		}
		return result;
	}

	@Override
	public List<EmpBean> getEmpsByMgr(String mgr) throws HrException {
		Manager m = mgrDao.findByName(mgr);
		if(m==null)
		{
			throw new HrException("经理？/未登录？");
		}
		List<Employee> emps = empDao.findByMgr(m);
		if(emps==null||emps.size()<1)
		{
			throw new HrException("该部门没有员工");
		}
		List<EmpBean> result = new ArrayList<EmpBean>();
		for(Employee e:emps)
		{
			result.add(new EmpBean(e.getName(),e.getPass(),e.getSalary()));
		}
		return result;
	}

	@Override
	public List<AppBean> getAppsByMgr(String mgr) throws HrException {
		Manager m =mgrDao.findByName(mgr);
		if(m==null)
		{
			throw new HrException("经理？/未登录？");
		}
		List<Employee> emps = empDao.findByMgr(m);
		if(emps==null|| emps.size()<1)
		{
			throw new HrException("该部门没有员工");
		}
		List<AppBean> result = new ArrayList<AppBean>();
		for(Employee e :emps)
		{
			List<Application> apps = appDao.findByEmp(e);
			if(apps !=null && apps.size()>0)
			{
				for(Application app:apps)
				{
					if(app.getResult()==false)
					{
						Attend attend = app.getAttend();
						result.add(new AppBean(app.getId(),e.getName(),attend.getType().getName(),app.getType().getName(),app.getReason()));
					}
				}
			}
		}
		return result;
	}

	@Override
	public void check(int appid, String mgrName, boolean result) {
		Application app = appDao.get(appid);
		CheckBack check = new CheckBack();
		check.setApp(app);
		if(result)
		{
			check.setResult(true);
			app.setResult(true);
			appDao.save(app);
			Attend attend = app.getAttend();
			attend.setType(app.getType());
			attendDao.update(attend);
		}
		else
		{
			check.setResult(false);
			app.setResult(true);
			appDao.save(app);
		}
		checkDao.save(check);
	}

}
