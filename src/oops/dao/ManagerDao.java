package oops.dao;

import java.util.List;

import oops.domain.Manager;

public interface ManagerDao {
	public Manager get(Integer id);
	public String save(Manager manager);
	public void update(Manager manager);
	public void delete(Manager manager);
	public void delete(Integer id);
	public List<Manager> findAll();
	public List<Manager> findByNameAndPass(Manager manager);
	public Manager findByName(String name);
}
