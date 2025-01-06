package com.DailyTasks.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
    name = "Simple Scheduler Configuration",
    description = "Configure Simple Scheduler"
)
public @interface Task8Configuration {

    @AttributeDefinition(
        name = "Cron Expression",
        description = "Cron expression for scheduling. Example: '0/30 * * * * ?' for every 30 seconds."
    )
    String scheduler_expression() default "0/10 * * * * ?";  // Every 30 seconds by default
}
