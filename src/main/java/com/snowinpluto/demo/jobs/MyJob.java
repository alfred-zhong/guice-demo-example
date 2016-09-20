package com.snowinpluto.demo.jobs;

import com.snowinpluto.demo.utils.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(DateUtil.formatDateYMDHMS(new Date()));
    }
}
