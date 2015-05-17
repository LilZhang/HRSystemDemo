package oops.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import oops.dao.*;
import oops.domain.*;
import oops.service.EmpManager;
import oops.vo.AttendBean;
import oops.vo.PaymentBean;

public class EmpManagerImpl implements EmpManager {
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
	public int validLogin(Manager mgr) {
		if(mgrDao.findByNameAndPass(mgr).size()>=1)
		{
			return LOGIN_MGR;
		}
		else if(empDao.findByNameAndPass(mgr).size()>=1)
		{
			return LOGIN_EMP;
		}
		else
		{
			return LOGIN_FAIL;
		}
	}

	@Override
	public void autoPunch() {
		System.out.println("自动插入旷工记录");
		List<Employee> emps=empDao.findAll();
		String dutyDay = new java.sql.Date(System.currentTimeMillis()).toString();
		for(Employee e :emps)
		{
			AttendType atype = typeDao.get(6);
			Attend a = new Attend();
			a.setDutyDay(dutyDay);
			a.setType(atype);
			if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<AM_LIMIT)
			{
				a.setIsCome(true);
			}
			else
			{
				a.setIsCome(false);
			}
			a.setEmployee(e);
			attendDao.save(a);
		}
	}

	@Override
	public void autoPay() {
		System.out.println("自动插入工资结算");
		List<Employee> emps=empDao.findAll();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -15);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String payMonth = sdf.format(c.getTime());
		for(Employee e:emps)
		{
			Payment pay = new Payment();
			double amount = e.getSalary();
			List<Attend> attends = attendDao.findByEmp(e);
			for(Attend a :attends)
			{
				amount +=a.getType().getAmerce();
			}
			pay.setPayMonth(payMonth);
			pay.setEmployee(e);
			pay.setAmount(amount);
			payDao.save(pay);
		}

	}

	@Override
	public int validPunch(String user, String dutyDay) {
		Employee emp = empDao.findByName(user);
		if(emp==null)
		{
			return NO_PUNCH;
		}
		List<Attend> attends = attendDao.findByEmpAndDutyDay(emp, dutyDay);
		if(attends==null||attends.size()<=0)
		{
			return NO_PUNCH;
		}
		else if(attends.size()==1
				&& attends.get(0).getIsCome()
				&& attends.get(0).getPunchTime()==null)
		{
			return COME_PUNCH;
		}
		else if(attends.size()==1
				&& attends.get(0).getPunchTime()==null)
		{
			return LEAVE_PUNCH;
		}
		else if(attends.size()==2)
		{
			if(attends.get(0).getPunchTime()==null
					&& attends.get(1).getPunchTime()==null)
			{
				return BOTH_PUNCH;
			}
			else if(attends.get(1).getPunchTime()==null)
			{
				return LEAVE_PUNCH;
			}
			else
			{
				return NO_PUNCH;
			}
		}
		return NO_PUNCH;
	}

	@Override
	public int punch(String user, String dutyDay, boolean isCome) {
		Employee emp = empDao.findByName(user);
		if(emp==null)
		{
			return PUNCH_FAIL;
		}
		Attend attend = attendDao.findByEmpAndDutyDayAndCome(emp, dutyDay, isCome);
		if(attend==null)
		{
			return PUNCH_FAIL;
		}
		if(attend.getPunchTime() !=null)
		{
			return PUNCHED;
		}
		System.out.println("===============打卡===============");
		int punchHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		attend.setPunchTime(new Date());
		if(isCome)
		{
			if(punchHour<COME_LIMIT)
			{
				attend.setType(typeDao.get(1));
			}
			else if(punchHour<LATE_LIMIT)
			{
				attend.setType(typeDao.get(4));
			}
		}
		else
		{
			if(punchHour>=LEAVE_LIMIT)
			{
				attend.setType(typeDao.get(1));
			}
			else if(punchHour>=EARLY_LIMIT)
			{
				attend.setType(typeDao.get(5));
			}
		}
		attendDao.update(attend);
		return PUNCH_SUCC;
	}

	@Override
	public List<PaymentBean> empSalary(String empName) {
		Employee emp =empDao.findByName(empName);
		List<Payment> pays=payDao.findByEmp(emp);
		List<PaymentBean> result = new ArrayList<PaymentBean>();
		for(Payment p:pays)
		{
			result.add(new PaymentBean(p.getPayMonth(),p.getAmount()));
		}
		return result;
	}

	@Override
	public List<AttendBean> unAttend(String empName) {
		AttendType type=typeDao.get(1);
		Employee emp = empDao.findByName(empName);
		List<Attend> attends=attendDao.findByEmpUnAttend(emp, type);
		List<AttendBean> result = new ArrayList<AttendBean>();
		for(Attend att:attends)
		{
			result.add(new AttendBean(att.getId(),att.getDutyDay(),att.getType().getName(),att.getPunchTime()));
		}
		return result;
	}

	@Override
	public List<AttendType> getAllType() {
		return typeDao.findAll();
	}

	@Override
	public boolean addApplication(int attId, int typeId, String reason) {
		Application app = new Application();
		Attend attend = attendDao.get(attId);
		AttendType type = typeDao.get(typeId);
		app.setAttend(attend);
		app.setType(type);
		if(reason !=null)
		{
			app.setReason(reason);
		}
		appDao.save(app);
		return true;
	}

}
