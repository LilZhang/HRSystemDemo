package oops.dao;

import java.util.List;

import oops.domain.Employee;
import oops.domain.Manager;

public interface EmployeeDao {
	public Employee get(Integer id);
	public Integer save(Employee employee);
	public void update(Employee employee);
	public void delete(Employee employee);
	public void delete(Integer id);
	public List<Employee> findAll();
	public List<Employee> findByNameAndPass(Employee emp);
	public Employee findByName(String name);
	public List<Employee> findByMgr(Manager mgr);
}
