package oops.action.base;

import oops.service.EmpManager;

import com.opensymphony.xwork2.ActionSupport;

public class EmpBaseAction extends ActionSupport {
	protected EmpManager mgr;
	public void setEmpManager(EmpManager mgr)
	{
		this.mgr=mgr;
	}
}
