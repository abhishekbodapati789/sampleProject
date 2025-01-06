package com.DailyTasks.core.listeners;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(
    service = EventHandler.class,
    immediate = true,
    property = {
        EventConstants.EVENT_TOPIC + "=org/apache/sling/jcr/resource/ResourceAdded"
    }
)
public class NodeCreationEventHandler implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(NodeCreationEventHandler.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void handleEvent(Event event) {
        String path = (String) event.getProperty("path");
        if (path != null) {
            LOG.info("Node created at path: {}", path);
            processNodeCreation(path);
        }
    }

    private void processNodeCreation(String path) {
        ResourceResolver resolver = null;

        
        try {
            Map<String, Object> param = new HashMap<>();
            param.put(ResourceResolverFactory.SUBSERVICE, "abhi"); 
            resolver = resourceResolverFactory.getServiceResourceResolver(param);
            Resource resource = resolver.getResource(path);
            if (resource != null) {
                LOG.info("Processing newly created node at path: {}", path);
            } else {
                LOG.warn("Resource not found for path: {}", path);
            }
        } catch (Exception e) {
            LOG.error("Error processing node creation at path: {}", path, e);
        } finally {
            if (resolver != null && resolver.isLive()) {
                resolver.close();
            }
        }
    }
}
