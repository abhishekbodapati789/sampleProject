package com.DailyTasks.core.schedulers;

import com.DailyTasks.core.Config.ResourceResolverService;
import com.DailyTasks.core.Config.WorkflowStatusConfiguration;
import com.DailyTasks.core.service.EmailService;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowService;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.Workflow;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.time.LocalDateTime;

@Component(
        immediate = true,
        service = Runnable.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=Workflow Status Scheduler"
        }
)
@Designate(ocd = WorkflowStatusConfiguration.class)
public class WorkflowStatusScheduler implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowStatusScheduler.class);

    @Reference
    private Scheduler scheduler;

    @Reference
    private WorkflowService workflowService;

    @Reference
    private ResourceResolverService resourceResolverService;

    @Reference
    private EmailService emailService;

    private String schedulerName;
    private String toEmail;
    private String ccEmail;
    private String fromEmail;
    private String subject;

    @Activate
    @Modified
    protected void activate(WorkflowStatusConfiguration configuration) {
        schedulerName = configuration.schedulerName();
        toEmail = configuration.toEmail();
        ccEmail = configuration.ccEmail();
        fromEmail = configuration.fromEmail();
        subject = configuration.subject();
        addScheduler(configuration);
    }

    @Deactivate
    protected void deactivate() {
        scheduler.unschedule(schedulerName);
    }

    private void addScheduler(WorkflowStatusConfiguration configuration) {
        if (configuration.enabled()) {
            ScheduleOptions options = scheduler.EXPR(configuration.cronExpression());
            options.name(schedulerName);
            options.canRunConcurrently(false);
            scheduler.schedule(this, options);
        } else {
            scheduler.unschedule(schedulerName);
        }
    }

    @Override
    public void run() {
        try {
            String workflowStatus = getWorkflowStatus();
            String content = "Hi,\n\nWorkflow status at " + LocalDateTime.now() + ":\n\n" + workflowStatus;
            emailService.sendEmail(toEmail, ccEmail, fromEmail, subject, content);
        } catch (Exception e) {
            LOGGER.error("Error during workflow status retrieval or email sending", e);
        }
    }

    private String getWorkflowStatus() throws WorkflowException {
        StringBuilder status = new StringBuilder();
        ResourceResolver resolver = resourceResolverService.getResourceResolver();
        Session session = resolver.adaptTo(Session.class);
        WorkflowSession wfSession = workflowService.getWorkflowSession(session);
        Workflow[] workflows = wfSession.getWorkflows(new String[]{"RUNNING", "COMPLETED"});
        for (Workflow workflow : workflows) {
            status.append("ID: ").append(workflow.getId()).append("\n")
                  .append("Payload: ").append(workflow.getWorkflowData().getPayload()).append("\n")
                  .append("State: ").append(workflow.getState()).append("\n\n");
        }
        return status.toString();
    }
}
