package com.DailyTasks.core.Config;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

@Component(
        service = ResourceResolverService.class
)
public class ResourceResolverServiceImpl implements ResourceResolverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceResolverServiceImpl.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public ResourceResolver getResourceResolver() {
        try {
            return resourceResolverFactory.getServiceResourceResolver(Collections.singletonMap(
                    ResourceResolverFactory.SUBSERVICE, "workflowServiceUser"));
        } catch (Exception e) {
            LOGGER.error("Failed to get ResourceResolver", e);
            return null;
        }
    }
}

