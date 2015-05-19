package oops.action;

import oops.service.EmpManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import static oops.service.EmpManager.*;

public class ProcessPunchAction extends ActionSupport {
	private EmpManager empMgr;
	private String tip;
	public void setEmpManager(EmpManager empMgr)
	{
		this.empMgr=empMgr;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public String come() throws Exception
	{
		return process(true);
	}
	
	public String leave() throws Exception
	{
		return process(false);
	}
	
	private String process(boolean isCome) throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		String user=(String)ctx.getSession().get(WebConstant.USER);
		System.out.println("---打卡---"+user);
		String dutyDay = new java.sql.Date(System.currentTimeMillis()).toString();
		int result = empMgr.punch(user, dutyDay, isCome);
		switch(result)
		{
		case PUNCH_FAIL:
			setTip("打卡失败");
			break;
		case PUNCHED:
			setTip("咦经打过卡，请勿重复打卡");
			break;
		case PUNCH_SUCC:
			setTip("打卡成功");
			break;
		}
		return SUCCESS;
	}
}
