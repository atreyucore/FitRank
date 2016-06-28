package br.com.fitrank.job;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class InitJobExtracao extends HttpServlet {

	private static final long serialVersionUID = -2541492224112127600L;

	public void init() throws ServletException {
		try {
			System.out.println("Iniciando Job Extracao");
			
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("JobExtracaoTrigger", "grupo1")
					.withSchedule(
							CronScheduleBuilder.cronSchedule("0 0 23 1/1 * ? *")
							).build();
			
			JobDetail job = JobBuilder.newJob(JobExtracao.class)
					.withIdentity("JobExtracaJob", "grupo1").build();
			
			Scheduler scheduler;
			
			try {
				scheduler = new StdSchedulerFactory().getScheduler();
		    	scheduler.start();
		    	scheduler.scheduleJob(job, trigger);
			}
	    	catch (SchedulerException e) {
	    		e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
