package oops.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import oops.action.base.MgrBaseAction;

public class LogoutAction extends MgrBaseAction implements ServletRequestAware {
	private HttpServletRequest request;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	public String execute()throws Exception
	{
		HttpSession session = request.getSession();
		session.invalidate();
		return SUCCESS;
	}

}
