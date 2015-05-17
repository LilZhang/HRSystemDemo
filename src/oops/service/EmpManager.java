package oops.service;

import java.util.List;

import oops.domain.AttendType;
import oops.domain.Manager;
import oops.vo.AttendBean;
import oops.vo.PaymentBean;

public interface EmpManager {
	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_EMP = 1;
	public static final int LOGIN_MGR = 2;
	
	public static final int NO_PUNCH = 0;
	public static final int COME_PUNCH = 1;
	public static final int LEAVE_PUNCH = 2;
	public static final int BOTH_PUNCH = 3;
	
	public static final int PUNCH_FAIL = 0;
	public static final int PUNCHED = 1;
	public static final int PUNCH_SUCC = 2;
	
	public static final int AM_LIMIT = 11;
	
	public static final int COME_LIMIT = 9;
	public static final int LATE_LIMIT = 11;
	public static final int LEAVE_LIMIT = 18;
	public static final int EARLY_LIMIT = 16;
	
	public int validLogin(Manager mgr);
	public void autoPunch();
	public void autoPay();
	public int validPunch(String user,String dutyDay);
	public int punch(String user,String dutyDay,boolean isCome);
	public List<PaymentBean> empSalary(String empName);
	public List<AttendBean> unAttend(String empName);
	public List<AttendType> getAllType();
	boolean addApplication(int attId , int typeId , String reason);
}
