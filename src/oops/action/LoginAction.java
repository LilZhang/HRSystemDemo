package oops.action;

import com.opensymphony.xwork2.ActionContext;

import oops.action.base.EmpBaseAction;
import oops.domain.Manager;
import static oops.service.EmpManager.*;

public class LoginAction extends EmpBaseAction
{
	private final String EMP_RESULT = "emp";
	private final String MGR_RESULT = "mgr";
	private Manager manager;
	private String vercode;
	private String tip;
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public String getVercode() {
		return vercode;
	}
	public void setVercode(String vercode) {
		this.vercode = vercode;
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
		String ver2=(String)ctx.getSession().get("rand");
		if(vercode.equalsIgnoreCase(ver2))
		{
			int result = mgr.validLogin(getManager());
			if(result==LOGIN_EMP)
			{
				ctx.getSession().put(WebConstant.USER, manager.getName());
				ctx.getSession().put(WebConstant.LEVEL, WebConstant.EMP_LEVEL);
				setTip("您已经成功登录");
				return EMP_RESULT;
			}
			else if(result==LOGIN_MGR)
			{
				ctx.getSession().put(WebConstant.USER, manager.getName());
				ctx.getSession().put(WebConstant.LEVEL, WebConstant.MGR_LEVEL);
				setTip("您已经成功登录");
				return MGR_RESULT;
			}
			else
			{
				setTip("用户名/密码不匹配");
				return ERROR;
			}
		}
		else
		{
			setTip("验证码不匹配，请重新输入");
			return ERROR;
		}
		
	}
}
