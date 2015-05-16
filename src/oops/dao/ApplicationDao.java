package oops.dao;

import java.util.List;

import oops.domain.Application;
import oops.domain.Employee;

public interface ApplicationDao {
	public Application get(Integer id);
	public Integer save(Application application);
	public void update(Application application);
	public void delete(Application application);
	public void delete(Integer id);
	public List<Application> findAll();
	public List<Application> findByEmp(Employee emp);
}
