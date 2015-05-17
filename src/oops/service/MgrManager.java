package oops.service;

import java.util.List;

import oops.domain.Employee;
import oops.exception.HrException;
import oops.vo.AppBean;
import oops.vo.EmpBean;
import oops.vo.SalaryBean;

public interface MgrManager {
	public void addEmp(Employee emp,String mgr)throws HrException;
	public List<SalaryBean> getSalaryByMgr(String mgr)throws HrException;
	public List<EmpBean> getEmpsByMgr(String mgr)throws HrException;
	public List<AppBean> getAppsByMgr(String mgr)throws HrException;
	public void check(int appid, String mgrName, boolean result);
}
