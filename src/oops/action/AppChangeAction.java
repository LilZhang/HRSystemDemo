package oops.action;

import java.util.List;

import oops.action.base.EmpBaseAction;

public class AppChangeAction extends EmpBaseAction {
	private List types;

	public List getTypes() {
		return types;
	}

	public void setTypes(List types) {
		this.types = types;
	}
	public String execute()throws Exception
	{
		setTypes(mgr.getAllType());
		return SUCCESS;
	}
}
