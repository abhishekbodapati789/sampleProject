package com.DailyTasks.core.listeners;

import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(
    service = EventHandler.class,
    immediate = true,
    property = {
        Constants.SERVICE_DESCRIPTION + "=Asset Edit Event Listener for Sling Job Submission",
        "event.topics=org/apache/sling/api/resource/Resource/CHANGED",
        "event.filter=(path=/content/dam/DailyTasks/images/*)"
    }
)
public class ImageEditJobSubmitter implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageEditJobSubmitter.class);

    @Reference
    private JobManager jobManager;

    @Override
    public void handleEvent(Event event) {
        try {
            // Extract the path from the event
            String path = (String) event.getProperty("path");
            if (path == null || !path.startsWith("/content/dam/DailyTasks/images")) {
                LOGGER.debug("Event not related to the target path: {}", path);
                return;
            }

            LOGGER.info("Detected an edit event on asset: {}", path);

            // Submit a Sling Job to handle publishing
            Map<String, Object> jobProperties = new HashMap<>();
            jobProperties.put("assetPath", path);

            jobManager.addJob("com/DailyTasks/core/jobs/publishAsset", jobProperties);
            LOGGER.info("Sling Job submitted for asset publishing: {}", path);

        } catch (Exception e) {
            LOGGER.error("Error submitting Sling Job for asset publishing.", e);
        }
    }
}
