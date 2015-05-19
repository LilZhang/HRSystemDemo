package oops.action;

import com.opensymphony.xwork2.ActionContext;

import oops.action.base.MgrBaseAction;
import oops.domain.Employee;

public class AddEmpAction extends MgrBaseAction {
	private Employee emp;
	private String tip;
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public String execute() throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String)ctx.getSession().get(WebConstant.USER);
		mgr.addEmp(emp, mgrName);
		setTip("新增员工成功");
		return SUCCESS;
	}
}
