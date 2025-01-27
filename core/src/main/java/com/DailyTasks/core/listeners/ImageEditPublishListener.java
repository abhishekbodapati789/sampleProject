package com.DailyTasks.core.listeners;

import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.Replicator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;

@Component(
    service = EventHandler.class,
    immediate = true,
    property = {
        Constants.SERVICE_DESCRIPTION + "=Asset Edit Event Listener for Auto-Publishing",
        "event.topics=org/apache/sling/api/resource/Resource/CHANGED",
        "event.filter=(path=/content/dam/DailyTasks/images/*)"
    }
)
public class ImageEditPublishListener implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageEditPublishListener.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private Replicator replicator;

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

            // Attempt to publish the asset
            publishAsset(path);

        } catch (Exception e) {
            LOGGER.error("Error processing asset edit event.", e);
        }
    }

    private void publishAsset(String assetPath) {
        Map<String, Object> params = new HashMap<>();
        params.put(ResourceResolverFactory.SUBSERVICE, "abhi");

        try (ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(params)) {
            Resource assetResource = resourceResolver.getResource(assetPath);

            if (assetResource != null) {
                String assetName = assetResource.getName();
                LOGGER.info("Attempting to publish the asset: {}", assetName);

                // Adapt the resource resolver to a JCR session
                Session session = resourceResolver.adaptTo(Session.class);
                if (session != null) {
                    replicator.replicate(session, ReplicationActionType.ACTIVATE, assetPath);
                    LOGGER.info("Asset '{}' successfully published at path: {}", assetName, assetPath);
                } else {
                    LOGGER.warn("Failed to adapt ResourceResolver to Session for path: {}", assetPath);
                }
            } else {
                LOGGER.warn("Resource not found at path: {}", assetPath);
            }
        } catch (Exception e) {
            LOGGER.error("Error while publishing the asset at path: {}", assetPath, e);
        }
    }
}
