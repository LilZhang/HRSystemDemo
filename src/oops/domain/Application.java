package oops.domain;

import java.io.Serializable;

public class Application implements Serializable {
	private static final long serialVersionUID=48L;
	private Integer id;
	private String reason;
	private boolean result;
	private Attend attend;
	private AttendType type;
	private CheckBack check;
	public Application() {
		
	}
	public Application(Integer id, String reason, boolean result,
			Attend attend, AttendType type, CheckBack check) {
		super();
		this.id = id;
		this.reason = reason;
		this.result = result;
		this.attend = attend;
		this.type = type;
		this.check = check;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public Attend getAttend() {
		return attend;
	}
	public void setAttend(Attend attend) {
		this.attend = attend;
	}
	public AttendType getType() {
		return type;
	}
	public void setType(AttendType type) {
		this.type = type;
	}
	public CheckBack getCheck() {
		return check;
	}
	public void setCheck(CheckBack check) {
		this.check = check;
	}
	
	
}
