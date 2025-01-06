package com.DailyTasks.core.schedulers;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Iterator;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = Task8Configuration.class)
public class Task8 implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Task8.class);

    private static final String SERVICE_USER = "abhi";
    private static final String PATH_TO_PUBLISH = "/content/DailyTasks/us/en";

    private String cronExpression;

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Activate
    protected void activate(Task8Configuration config) {
        cronExpression = config.scheduler_expression();
        log.info("Scheduler activated with cron expression: {}", cronExpression);
    }

    @Deactivate
    protected void deactivate() {
        log.info("Scheduler deactivated.");
    }

    @Override
    public void run() {
        log.info("Executing scheduled task to publish child pages under {}", PATH_TO_PUBLISH);
        try {
            publishChildPages();
        } catch (Exception e) {
            log.error("Error during publishing child pages", e);
        }
    }

    private void publishChildPages() {
        try (ResourceResolver resolver = getServiceResourceResolver()) {
            if (resolver != null) {
                Resource parentResource = resolver.getResource(PATH_TO_PUBLISH);
                if (parentResource != null) {
                    Iterator<Resource> childPages = parentResource.listChildren();
                    while (childPages.hasNext()) {
                        Resource child = childPages.next();
                        String childPath = child.getPath();

                        // Log the path of the page being published
                        log.info("Publishing page: {}", childPath);
                    }
                } else {
                    log.warn("Parent resource not found at path: {}", PATH_TO_PUBLISH);
                }
            } else {
                log.error("Failed to obtain ResourceResolver.");
            }
        } catch (Exception e) {
            log.error("Error while publishing pages", e);
        }
    }

    private ResourceResolver getServiceResourceResolver() {
        try {
            return resourceResolverFactory.getServiceResourceResolver(
                    Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, SERVICE_USER)
            );
        } catch (Exception e) {
            log.error("Failed to get service resource resolver", e);
        }
        return null;
    }
}
