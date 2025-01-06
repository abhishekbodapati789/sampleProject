package com.DailyTasks.core.listeners;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Node;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Component(         
    service = WorkflowProcess.class,
    property = {"process.label=Add Expiry Date to Page"}
) 

public class AddExpiryDateWorkflowProcess implements WorkflowProcess {

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) {
        String payloadPath = workItem.getWorkflowData().getPayload().toString();

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(
                Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "writeService"))) {

            Resource resource = resolver.getResource(payloadPath);

            if (resource != null) {
                Node pageNode = resource.adaptTo(Node.class);

                if (pageNode != null && pageNode.hasNode("jcr:content")) {
                    Node contentNode = pageNode.getNode("jcr:content");

                    // Set expiry property
                    String currentDateTime = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    contentNode.setProperty("expiry", currentDateTime);

                     resolver.commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
