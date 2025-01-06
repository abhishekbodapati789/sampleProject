package com.DailyTasks.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = WorkflowStatusConfiguration.CONFIGURATION_NAME,
        description = WorkflowStatusConfiguration.CONFIGURATION_DESCRIPTION
)
public @interface WorkflowStatusConfiguration {

    String CONFIGURATION_NAME = "Workflow Status Configuration";
    String CONFIGURATION_DESCRIPTION = "This configuration captures the details for getting workflow status and sending email";
    String DEFAULT_EMAIL_ADDRESS = "abhishekbodapati789@gmail.com";

    @AttributeDefinition(
            name = "Scheduler Name",
            description = "Enter a unique identifier that represents the name of the scheduler",
            type = AttributeType.STRING
    )
    String schedulerName() default CONFIGURATION_NAME;

    @AttributeDefinition(
            name = "Enabled",
            description = "Check the box to enable the scheduler",
            type = AttributeType.BOOLEAN
    )
    boolean enabled() default false;

    @AttributeDefinition(
            name = "Cron Expression",
            description = "Cron expression which will decide how the scheduler will run",
            type = AttributeType.STRING
    )
    String cronExpression() default "0 * * * * ?";

    @AttributeDefinition(
            name = "To Email",
            description = "Enter the email address of the recipient in the TO field",
            type = AttributeType.STRING
    )
    String toEmail() default DEFAULT_EMAIL_ADDRESS;

    @AttributeDefinition(
            name = "CC Email",
            description = "Enter the email address of the recipient in the CC field",
            type = AttributeType.STRING
    )
    String ccEmail() default DEFAULT_EMAIL_ADDRESS;

    @AttributeDefinition(
            name = "From Email",
            description = "Enter the email address of the sender",
            type = AttributeType.STRING
    )
    String fromEmail() default DEFAULT_EMAIL_ADDRESS;

    @AttributeDefinition(
            name = "Subject",
            description = "Enter the subject of the email",
            type = AttributeType.STRING
    )
    String subject() default CONFIGURATION_NAME;
}
