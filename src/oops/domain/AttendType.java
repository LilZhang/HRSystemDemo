package oops.domain;

import java.io.Serializable;

public class AttendType implements Serializable {
	private static final long serialVersionUID = 48L;
	private Integer id;
	private String name;
	private double amerce;
	public AttendType() {
		
	}
	public AttendType(Integer id, String name, double amerce) {
		
		this.id = id;
		this.name = name;
		this.amerce = amerce;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmerce() {
		return amerce;
	}
	public void setAmerce(double amerce) {
		this.amerce = amerce;
	}
	
	
}
