package oops.dao;

import java.util.List;

import oops.domain.CheckBack;

public interface CheckBackDao {
	public CheckBack get(Integer id);
	public Integer save(CheckBack checkBack);
	public void update(CheckBack checkBack);
	public void delete(CheckBack checkBack);
	public void delete(Integer id);
	public List<CheckBack> findAll();
}
