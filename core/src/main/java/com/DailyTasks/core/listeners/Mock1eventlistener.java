package com.DailyTasks.core.listeners;   

import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventListener.class, immediate = true)
public class Mock1eventlistener  implements EventListener {

    private static final Logger LOG = LoggerFactory.getLogger(Mock1eventlistener.class);

    @Reference
    private SlingRepository slingRepository;

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    private Session session;

    @Activate
    protected void activate() {
        try {
           
            session = slingRepository.loginService("system-user", null);
            session.getWorkspace().getObservationManager().addEventListener(
                this,                             // Event Listener instance
                Event.NODE_ADDED,                 // Event Type: NODE_ADDED
                "/content",                       // Path to monitor
                true,                             // Is Deep (true = includes child nodes)
                null,                             // UUIDs filter (null = no filter)
                null,                             // Node types filter (null = no filter)
                false                             // NoLocal: exclude local events
            );

            LOG.info("PageCreationListener successfully registered.");
        } catch (RepositoryException e) {
            LOG.error("Error while registering PageCreationListener", e);
        }
    }

    @Override
    public void onEvent(EventIterator events) {
        while (events.hasNext()) {
            try {
                Event event = events.nextEvent();
                String path = event.getPath(); // Get the path of the added node
                LOG.info("A new page or node was added at path: {}", path);
            } catch (RepositoryException e) {
                LOG.error("Error processing event", e);
            }
        }
    }

    @Deactivate
    protected void deactivate() {
        if (session != null) {
            session.logout();
            LOG.info("PageCreationListener unregistered successfully.");
        }
    }
}


