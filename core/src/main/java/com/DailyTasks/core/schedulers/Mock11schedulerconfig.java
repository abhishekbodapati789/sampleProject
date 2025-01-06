package com.DailyTasks.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="ocd",
description="configration")
public @interface Mock11schedulerconfig {
@AttributeDefinition(
    name="properties",description = "message"
)
String cron_expression()default"0/30 * * * * ?";
}
