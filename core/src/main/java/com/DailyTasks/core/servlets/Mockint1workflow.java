// package com.DailyTasks.core.servlets;

// import com.adobe.granite.workflow.WorkflowException;
// import com.adobe.granite.workflow.exec.WorkflowData;
// import com.adobe.granite.workflow.exec.WorkflowProcess;
// import com.adobe.granite.workflow.metadata.MetaDataMap;
// import org.osgi.service.component.annotations.Component;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Component(
//     service = WorkflowProcess.class,
//     property = {"process.label=Custom Workflow Step"}
// )
// public class Mockint1workflow implements WorkflowProcess {

//     private static final Logger LOG = LoggerFactory.getLogger(Mockint1workflow.class);

//     @Override
//     public void execute(WorkflowData workflowData, MetaDataMap metaDataMap) throws WorkflowException {
//         LOG.info("Executing Custom Workflow Step...");

        
//         String payloadPath = workflowData.getPayload().toString();
//         LOG.info("Payload Path: {}", payloadPath);
        
//         // Custom logic goes here (e.g., updating a property, performing validation, etc.)
//         LOG.info(" Workflow step executed successfully for path: {}", payloadPath);
//     }
// }
