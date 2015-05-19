package oops.action;

import com.opensymphony.xwork2.ActionContext;

import oops.action.base.MgrBaseAction;

public class CheckAppAction extends MgrBaseAction {
	private int appid;
	private String result;
	private String tip;
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String execute()throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String)ctx.getSession().get(WebConstant.USER);
		
		if(result.equals("pass"))
		{
			mgr.check(appid, mgrName, true);
		}
		else if(result.equals("deny"))
		{
			mgr.check(appid, mgrName, false);
		}
		else
		{
			throw new Exception("参数丢失");
		}
		setTip("处理员工申请成功");
		return SUCCESS;
	}
}
