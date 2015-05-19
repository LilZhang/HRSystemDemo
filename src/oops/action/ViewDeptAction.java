package oops.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import oops.action.base.MgrBaseAction;
import oops.vo.SalaryBean;

public class ViewDeptAction extends MgrBaseAction {
	private List sals;

	public List getSals() {
		return sals;
	}

	public void setSals(List sals) {
		this.sals = sals;
	}
	
	public String execute() throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String)ctx.getSession().get(WebConstant.USER);
		List<SalaryBean> result = mgr.getSalaryByMgr(mgrName);
		System.out.println("--------------" + result);
		setSals(result);
		return SUCCESS;
	}
}
