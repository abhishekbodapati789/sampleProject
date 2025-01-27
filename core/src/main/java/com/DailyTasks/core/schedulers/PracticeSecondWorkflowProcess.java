// package com.DailyTasks.core.schedulers;

// import com.adobe.granite.workflow.WorkflowException;
// import com.adobe.granite.workflow.WorkflowSession;
// import com.adobe.granite.workflow.exec.WorkItem;
// import com.adobe.granite.workflow.exec.WorkflowProcess;
// import com.adobe.granite.workflow.metadata.MetaDataMap;
// import org.osgi.service.component.annotations.Component;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Component(service = WorkflowProcess.class,
//         property = {"process.label=Practice Second Workflow Process"})
// public class PracticeSecondWorkflowProcess implements WorkflowProcess {

//     private static final Logger logger = LoggerFactory.getLogger(PracticeSecondWorkflowProcess.class);

//     @Override
//     public void execute(WorkItem workItem, WorkflowSession wfSession, MetaDataMap metaDataMap) throws WorkflowException {
//         logger.error("PracticeSecondWorkflowProcess called >>>>>>>>>");

//         try {
//             // Retrieve the MetaDataMap from the workflow
//             MetaDataMap workflowMetaDataMap = workItem.getWorkflow().getMetaDataMap();

//             // Access the values set in the previous workflow step
//             String name = workflowMetaDataMap.get("user", String.class);
//             String age = workflowMetaDataMap.get("age", String.class);

//             // Log the retrieved values
//             logger.info("Retrieved values from MetaDataMap - Name: {}, Age: {}", name, age);
//         } catch (Exception e) {
//             logger.error("Error while accessing the MetaDataMap in PracticeSecondWorkflowProcess", e);
//             throw new WorkflowException("Error in PracticeSecondWorkflowProcess", e);
//         }
//     }
// }
