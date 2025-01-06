package com.DailyTasks.core.listeners;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

@Component(
        service = EventHandler.class,
        immediate = true,
        property = {
                "event.topics=com/day/cq/replication/ReplicationEvent"
        }
)
public class Task10 implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(Task10.class);

    private static final String PATH_TO_PUBLISH = "/content/DailyTasks/us/en/task9";

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void handleEvent(Event event) {
        try (ResourceResolver resolver = getServiceResourceResolver()) {
            if (resolver != null) {
                
                Resource pageResource = resolver.getResource(PATH_TO_PUBLISH);
                if (pageResource != null) {
                    LOG.info("Page found and ready for publishing: {}", PATH_TO_PUBLISH);
                   
                    triggerPublish(resolver, PATH_TO_PUBLISH);
                } else {
                    LOG.warn("Page not found at path: {}", PATH_TO_PUBLISH);
                }
            } else {
                LOG.error("Failed to obtain service ResourceResolver.");
            }
        } catch (Exception e) {
            LOG.error("Error occurred while handling the page at path: {}", PATH_TO_PUBLISH, e);
        }
    }


    private ResourceResolver getServiceResourceResolver() {
        try {
            return resourceResolverFactory.getServiceResourceResolver(
                    Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "abhi")  // Ensure the service user has appropriate permissions
            );
        } catch (Exception e) {
            LOG.error("Failed to get service ResourceResolver.", e);
        }
        return null;
    }

    private void triggerPublish(ResourceResolver resolver, String pagePath) {
        try {
           
            resolver.adaptTo(SlingScriptHelper.class).getRequest().getResourceResolver().commit();
            LOG.info("Page successfully published: {}", pagePath);
        } catch (Exception e) {
            LOG.error("Error during replication of page: {}", pagePath, e);
        }
    }
}
