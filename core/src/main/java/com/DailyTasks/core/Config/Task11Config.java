package com.DailyTasks.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Task11 Scheduler Configuration")
public @interface Task11Config {

    @AttributeDefinition(
        name = "Cron Expression",
        description = "Cron expression to define scheduler frequency. Example: '0 */3 * * * ?' for every 3 minutes"
    )
    String cronExpression() default "0 */3 * * * ?";
}
