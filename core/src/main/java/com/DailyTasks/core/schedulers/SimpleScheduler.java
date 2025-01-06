package com.DailyTasks.core.schedulers;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = SimpleSchedulerConfiguration.class)
public class SimpleScheduler implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SimpleScheduler.class);

    private String cronExpression;

    @Activate
    protected void activate(SimpleSchedulerConfiguration config) {
        cronExpression = config.scheduler_expression();
        try {
            log.info(" Happy Christmas And Scheduler will run for every : {}", cronExpression);

        } catch (Exception e) {
            log.error("Failed to register scheduler", e);
        }
    }

    @Deactivate
    protected void deactivate() {
        try {
            log.info("Scheduler deactivated.");
           
        } catch (Exception e) {
            log.error("Failed to unregister scheduler", e);
        }
    }

    @Override
    public void run() {
        log.info("Executing scheduled task based on cron expression: {}", cronExpression);
    }
}
