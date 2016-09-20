package com.snowinpluto.demo;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

public class AppQuartzModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(JobFactory.class).to(MyJobFactory.class);
    }

    public static class MyJobFactory implements JobFactory {

        @Inject
        private Injector injector;

        @Override
        public Job newJob(TriggerFiredBundle triggerFiredBundle,
                          Scheduler scheduler) throws SchedulerException {
            return injector.getInstance(triggerFiredBundle.getJobDetail().getJobClass());
        }
    }
}
