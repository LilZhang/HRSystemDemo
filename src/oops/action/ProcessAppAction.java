package oops.action;

import oops.action.base.EmpBaseAction;

public class ProcessAppAction extends EmpBaseAction {
	private int attId;
	private int typeId;
	private String reason;
	private String tip;
	public int getAttId() {
		return attId;
	}
	public void setAttId(int attId) {
		this.attId = attId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String execute()throws Exception
	{
		boolean result = mgr.addApplication(attId, typeId, reason);
		if(result)
		{
			setTip("…Í«Î≥…π¶");
		}
		else
		{
			setTip("…Í«Î ß∞‹£¨«Î≤ª“™÷ÿ∏¥…Í«Î");
		}
		return SUCCESS;
	}
}
