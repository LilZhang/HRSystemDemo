package oops.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import oops.action.base.EmpBaseAction;
import oops.vo.AttendBean;

public class ViewUnAttendAction extends EmpBaseAction {
	private List<AttendBean> unAttend;
	public void setUnAttend(List<AttendBean> unAttend)
	{
		this.unAttend = unAttend;
	}
	public List<AttendBean> getUnAttend()
	{
		return this.unAttend;
	}
	public String execute()
		throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String user = (String)ctx.getSession()
			.get(WebConstant.USER);
		List<AttendBean> result = mgr.unAttend(user);
		setUnAttend(result);
		return SUCCESS;
	}
}
