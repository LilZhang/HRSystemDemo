package oops.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import oops.action.base.MgrBaseAction;

public class ViewEmpAction extends MgrBaseAction {
	private List emps;
	public void setEmps(List emps)
	{
		this.emps = emps;
	}
	public List getEmps()
	{
		return this.emps;
	}
	public String execute()
		throws Exception
	{		
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String)ctx.getSession()
			.get(WebConstant.USER);
		setEmps(mgr.getEmpsByMgr(mgrName));
		return SUCCESS;
	}
}
