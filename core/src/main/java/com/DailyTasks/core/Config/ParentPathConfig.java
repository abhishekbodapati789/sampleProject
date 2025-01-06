package com.DailyTasks.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Pathconfig",description = "Give Path")
public @interface ParentPathConfig {

    @AttributeDefinition(
        name = "Pathconfig",
        description = "Enter the path",
        type = AttributeType.STRING
    )public String getPath() default "/content/we-retail/us/en";


}
