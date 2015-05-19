package oops.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import oops.action.base.EmpBaseAction;
import oops.vo.PaymentBean;

public class ViewSalaryAction extends EmpBaseAction {
	private List salarys;
	public void setSalarys(List salarys)
	{
		this.salarys = salarys;
	}
	public List getSalarys()
	{
		return this.salarys;
	}
	public String execute()
		throws Exception
	{		
		ActionContext ctx = ActionContext.getContext();	
		String user = (String)ctx.getSession()
			.get(WebConstant.USER);
		List<PaymentBean> salarys =  mgr.empSalary(user);
		setSalarys(salarys);
		return SUCCESS;
	}
}
