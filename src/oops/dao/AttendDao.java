package oops.dao;

import java.util.List;

import oops.domain.Attend;
import oops.domain.AttendType;
import oops.domain.Employee;

public interface AttendDao {
	public Attend get(Integer id);
	public Integer save(Attend attend);
	public void update(Attend attend);
	public void delete(Attend attend);
	public void delete(Integer id);
	public List<Attend> findAll();
	public List<Attend> findByEmp(Employee employee);
	public List<Attend> findByEmpAndDutyDay(Employee employee,String dutyDay);
	public Attend findByEmpAndDutyDayAndCome(Employee emp,String dutyDay,boolean isCome);
	public List<Attend> findByEmpUnAttend(Employee emp,AttendType type);
}
