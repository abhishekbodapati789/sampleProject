package com.DailyTasks.core.schedulers;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class,immediate=true)

public class mock11scheduler implements Runnable {
private  static final Logger log=LoggerFactory.getLogger(mock11scheduler.class);
private String cron;


@Activate
protected void getactive(Mock11schedulerconfig config){
cron=config.cron_expression();
log.info("activated mock11 sheduler",cron);
}

@Deactivate
protected void deactivate(){
    log.info("deactivated");
}


@Override
public void run(){
    log.info("activated and executed");
}
}
