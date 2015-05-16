package oops.dao;

import java.util.List;

import oops.domain.AttendType;

public interface AttendTypeDao {
	public AttendType get(Integer id);
	public Integer save(AttendType attendType);
	public void update(AttendType attendType);
	public void delete(AttendType attendType);
	public void delete(Integer id);
	public List<AttendType> findAll();
}
