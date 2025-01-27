package com.DailyTasks.core.schedulers;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = WorkflowProcess.class,
        property = {"process.label=Practice Custom Workflow Process" })
public class PracticeCustomWorkflowProcess implements WorkflowProcess{

    protected final Logger logger = LoggerFactory.getLogger(PracticeCustomWorkflowProcess.class);

    public void execute(WorkItem workItem, WorkflowSession wfSession,
        MetaDataMap metaDataMap) throws WorkflowException {

        logger.error("PracticeCustomWorkflowProcess called >>>>>>>>");

        String pathInfo = workItem.getWorkflow().getMetaDataMap().get("pathInfo", String.class);
        logger.error("pathInfo >>>>>>> {}", pathInfo);
    }
}