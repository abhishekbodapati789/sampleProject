
// @Component(service=EventHandler.class,immediate = true,
// property={EventConstants.EVENT_TOPIC + "=com/day/cq/replication"})

package com.DailyTasks.core.listeners;

import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    service = EventHandler.class,
    immediate = true,
    property = {
        EventConstants.EVENT_TOPIC + "=org/apache/sling/jcr/resource/ResourceAdded"
    }
)
public class Mock1Eventhandle implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(Mock1Eventhandle.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void handleEvent(Event event) {
        try {
            String[] paths = (String[]) event.getProperty("paths");
            if (paths != null) {
                for (String path : paths) {
                    LOG.info("Page registered at path: {}", path);
                }
            } else {
                LOG.warn("No page paths found in the event.");
            }
        } catch (Exception e) {
            LOG.error("Error while handling page registration event", e);
        }
    }
}

