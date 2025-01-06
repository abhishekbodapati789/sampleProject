package com.DailyTasks.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
    name="abhi",
    description="configration"
)
public @interface Mock2Config {

@AttributeDefinition(
name="abhi",
description="cron Expression")

String cron_scheduler() 
default "0/30 * * * * ?";  

}
