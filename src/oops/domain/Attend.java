package oops.domain;

import java.io.Serializable;
import java.util.Date;

public class Attend implements Serializable {
	private static final long serialVersionUID = 48L;
	private Integer id;
	private String dutyDay;
	private Date punchTime;
	private boolean isCome;
	private AttendType type;
	private Employee employee;
	public Attend() {
		
	}
	public Attend(Integer id, String dutyDay, Date punchTime, boolean isCome,
			AttendType type, Employee employee) {
		super();
		this.id = id;
		this.dutyDay = dutyDay;
		this.punchTime = punchTime;
		this.isCome = isCome;
		this.type = type;
		this.employee = employee;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDutyDay() {
		return dutyDay;
	}
	public void setDutyDay(String dutyDay) {
		this.dutyDay = dutyDay;
	}
	public Date getPunchTime() {
		return punchTime;
	}
	public void setPunchTime(Date punchTime) {
		this.punchTime = punchTime;
	}
	public boolean getIsCome() {
		return isCome;
	}
	public void setIsCome(boolean isCome) {
		this.isCome = isCome;
	}
	public AttendType getType() {
		return type;
	}
	public void setType(AttendType type) {
		this.type = type;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public int hashCode() {
		if(getIsCome()){
			return dutyDay.hashCode()+29*employee.hashCode()+1;
		}
		else{
			return dutyDay.hashCode()+29*employee.hashCode();
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if(obj != null && obj.getClass()==Attend.class){
			Attend attend =(Attend)obj;
			return getEmployee().equals(attend.getEmployee())
					&& getDutyDay().equals(attend.getDutyDay())
					&& getIsCome() == attend.getIsCome();
		}
		return false;
	}	
}
