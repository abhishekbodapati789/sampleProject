// package com.example.aem.listeners;

// import org.apache.sling.api.resource.ModifiableValueMap;
// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.jcr.api.SlingRepository;
// import org.osgi.service.component.annotations.Component;
// import org.osgi.service.component.annotations.Reference;
// import org.osgi.service.event.Event;
// import org.osgi.service.event.EventConstants;
// import org.osgi.service.event.EventHandler;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import javax.jcr.RepositoryException;
// import javax.jcr.Session;
// import org.apache.sling.api.resource.PersistenceException;

// @Component(
//     service = EventHandler.class,
//     immediate = true,
//     property = {
//         EventConstants.EVENT_TOPIC + "=com/day/cq/replication"
//     }
// )
// public class Eventhandlertask implements EventHandler {

//     private static final Logger LOG = LoggerFactory.getLogger(Eventhandlertask.class);

//     @Reference
//     private SlingRepository slingRepository; // Use SlingRepository to obtain ResourceResolver

//     @Override
//     public void handleEvent(Event event) {
//         String[] paths = (String[]) event.getProperty("paths");
//         if (paths != null && paths.length > 0) {
//             for (String path : paths) {
//                 logPublishedPagePath(path);
//                 updatePageProperty(path);
//             }
//         } else {
//             LOG.warn("Page paths not found in event: {}", event);
//         }
//     }

//     private void logPublishedPagePath(String pagePath) {
//         LOG.info("Page published: {}", pagePath);
//     }

//     private void updatePageProperty(String pagePath) {
//         ResourceResolver resolver = null;
//         try {
//             resolver = getResourceResolver(); // Obtain ResourceResolver
//             if (resolver != null) {
//                 Resource pageResource = resolver.getResource(pagePath + "/jcr:content");
//                 if (pageResource != null) {
//                     ModifiableValueMap properties = pageResource.adaptTo(ModifiableValueMap.class);
//                     if (properties != null) {
//                         properties.put("changed", true); // Set the 'changed' property
//                         resolver.commit(); // Save changes
//                         LOG.info("Property 'changed' set to true for page: {}", pagePath);
//                     } else {
//                         LOG.warn("Failed to adapt resource to ModifiableValueMap for path: {}", pagePath);
//                     }
//                 } else {
//                     LOG.warn("Resource not found for path: {}", pagePath);
//                 }
//             }
//         } catch (PersistenceException e) {
//             LOG.error("Error updating page property for path {}: {}", pagePath, e.getMessage(), e);
//         } finally {
//             if (resolver != null && resolver.isLive()) {
//                 resolver.close(); // Ensure the resolver is closed
//             }
//         }
//     }

//     private ResourceResolver getResourceResolver() {
//         ResourceResolver resolver = null;
//         try {
//             // Obtain a ResourceResolver from SlingRepository using a system or service user
//             Session session = slingRepository.loginService("writeService", null); // Adjust service name as needed
//             if (session != null) {
//                 resolver = session.adaptTo(ResourceResolver.class);
//             }
//         } catch (RepositoryException e) {
//             LOG.error("Failed to obtain ResourceResolver", e);
//         }
//         return resolver;
//     }
// }