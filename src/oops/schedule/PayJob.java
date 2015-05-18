package oops.schedule;

import oops.service.EmpManager;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class PayJob extends QuartzJobBean {
	private boolean isRunning = false;
	private EmpManager empMgr;
	
	public void setEmpMgr(EmpManager empMgr) {
		this.empMgr = empMgr;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		if(!isRunning)
		{
			System.out.println("��ʼ�����Զ����㹤��");
			isRunning=true;
			empMgr.autoPay();
			isRunning=false;
		}
	}

}
