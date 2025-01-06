package com.DailyTasks.core.schedulers;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=Runnable.class,immediate=true)
@Designate(ocd=Mock2Config.class)
public class Mock2 implements Runnable {
private static final Logger log=LoggerFactory.getLogger(Mock2.class);
private String cronexpression;

@Activate
protected void active(Mock2Config config){
cronexpression=config.cron_scheduler();
log.info("hello",cronexpression);
}

@Deactivate
protected void deactive(){
    log.info("deactivate");
}


@Override
public void run(){
   log.info("executed"); 
}













}








