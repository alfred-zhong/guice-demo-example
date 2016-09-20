package com.snowinpluto.demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.snowinpluto.demo.AppQuartzModule.MyJobFactory;
import com.snowinpluto.demo.jobs.MyJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class AppServletContextListener extends GuiceServletContextListener {

    public static Injector injector = null;

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(new AppModule(),
                                        new AppServletModule(),
                                        new AppMyBatisModule(),
                                        new AppMvcModule(),
                                        new AppQuartzModule());


        // Object that contains the job class.
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                                        .withIdentity("jobId", "jobGroup").build();

        // Create the trigger that will instantiate and execute the job.
        // Execute the job with a 3 seconds interval.
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("triggerId")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                             .withIntervalInSeconds(3).repeatForever())
                .build();

        try {
            // Retrieve the Quartz scheduler to schedule the job.
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            // Here we tell the Quartz scheduler to use our factory.
            scheduler.setJobFactory(injector.getInstance(MyJobFactory.class));
            scheduler.scheduleJob(jobDetail, trigger);

            // Start the scheduler.
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return injector;
    }
}
