package oops.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Employee implements Serializable {
	private static final long serialVersionUID = 48L;
	private Integer id;
	private String name;
	private String pass;
	private double salary;
	private Manager manager;
	private Set<Attend> attends = new HashSet<Attend>();
	private Set<Payment> payments = new HashSet<Payment>();
	public Employee() {
		
	}
	public Employee(Integer id, String name, String pass, double salary,
			Manager manager, Set<Attend> attends, Set<Payment> payments) {
		
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.salary = salary;
		this.manager = manager;
		this.attends = attends;
		this.payments = payments;
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public Set<Attend> getAttends() {
		return attends;
	}
	public void setAttends(Set<Attend> attends) {
		this.attends = attends;
	}
	public Set<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	@Override
	public int hashCode() {
		return name.hashCode()+pass.hashCode()*17;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if(obj !=null && obj.getClass()==Employee.class){
			Employee employee=(Employee)obj;
			return this.getName().equals(employee.getName())
					&& this.getPass().equals(employee.getPass());
		}
		return false;
		
	}
	
	
	
}
