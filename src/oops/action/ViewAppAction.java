package oops.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import oops.action.base.MgrBaseAction;

public class ViewAppAction extends MgrBaseAction {
	private List Apps;

	public List getApps() {
		return Apps;
	}

	public void setApps(List apps) {
		Apps = apps;
	}
	public String execute()throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String)ctx.getSession().get(WebConstant.USER);
		setApps(mgr.getAppsByMgr(mgrName));
		return SUCCESS;
	}
}
