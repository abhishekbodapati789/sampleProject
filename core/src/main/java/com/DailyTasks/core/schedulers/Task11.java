// package com.DailyTasks.core.schedulers;

// import com.day.cq.replication.Replicator;
// import com.DailyTasks.core.Config.Task11Config;
// import com.day.cq.replication.ReplicationActionType;
// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.api.resource.ResourceResolverFactory;
// import org.osgi.service.component.annotations.Activate;
// import org.osgi.service.component.annotations.Component;
// import org.osgi.service.component.annotations.Reference;
// import org.osgi.service.metatype.annotations.Designate;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import java.util.Calendar;
// import java.util.Iterator;
// import java.util.Map;

// import javax.jcr.Session;

// @Component(service = Runnable.class, immediate = true)
// @Designate(ocd = Task11Config.class)
// public class Task11 implements Runnable {

//     private static final Logger LOG = LoggerFactory.getLogger(Task11.class);

//     @Reference
//     private ResourceResolverFactory resourceResolverFactory;

//     @Reference
//     private Replicator replicator;

//     private final String EXPIRED_DATE_PROPERTY = "expiryDate";

//     @Activate
//     protected void activate(Task11Config config) {
//         LOG.info("Task11 Scheduler activated with Cron Expression: {}", config.cronExpression());
//     }

//     @Override
//     public void run() {
//         LOG.info("Running Task11 Scheduler...");
//         try (ResourceResolver resourceResolver = getServiceResourceResolver()) {
//             if (resourceResolver == null) {
//                 LOG.error("Unable to get Service Resource Resolver.");
//                 return;
//             }

//             String query = String.format(
//                 "SELECT * FROM [cq:Page] AS page WHERE [expiryDate] IS NOT NULL"
//             );

//             Iterator<Resource> resources = resourceResolver.findResources(query, "JCR-SQL2");

//             Calendar currentTime = Calendar.getInstance();
//             while (resources.hasNext()) {
//                 Resource resource = resources.next();
//                 Calendar expiryDate = resource.getValueMap().get(EXPIRED_DATE_PROPERTY, Calendar.class);
//                 if (expiryDate == null) continue;

//                 String pagePath = resource.getPath();
//                 if (expiryDate.equals(currentTime)) {
//                     LOG.info("Publishing page: {}", pagePath);
//                     replicator.replicate(resourceResolver.adaptTo(Session.class), ReplicationActionType.ACTIVATE, pagePath);
//                 } else if (expiryDate.before(currentTime)) {
//                     LOG.info("Unpublishing page: {}", pagePath);
//                     replicator.replicate(resourceResolver.adaptTo(Session.class), ReplicationActionType.DEACTIVATE, pagePath);
//                 }
//             }
//         } catch (Exception e) {
//             LOG.error("Error running Task11 Scheduler: {}", e.getMessage(), e);
//         }
//     }

//     private ResourceResolver getServiceResourceResolver() {
//         try {
//             return resourceResolverFactory.getServiceResourceResolver(Map.of(ResourceResolverFactory.SUBSERVICE, "schedulerService"));
//         } catch (Exception e) {
//             LOG.error("Error obtaining service resource resolver: {}", e.getMessage(), e);
//             return null;
//         }
//     }
// }
