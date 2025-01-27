package com.DailyTasks.core.schedulers;

import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.Replicator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

@Component(
    service = JobConsumer.class,
    immediate = true,
    property = {
        Constants.SERVICE_DESCRIPTION + "=Job Consumer for Publishing Assets",
        JobConsumer.PROPERTY_TOPICS + "=com/DailyTasks/core/jobs/publishAsset"
    }
)
public class AssetPublishJobConsumer implements JobConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssetPublishJobConsumer.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private Replicator replicator;

    @Override
    public JobResult process(Job job) {
        String assetPath = (String) job.getProperty("assetPath");
        if (assetPath == null || assetPath.isEmpty()) {
            LOGGER.warn("Invalid asset path received in job.");
            return JobResult.FAILED;
        }

        LOGGER.info("Processing Sling Job for publishing asset: {}", assetPath);

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
                    return JobResult.OK;
                } else {
                    LOGGER.warn("Failed to adapt ResourceResolver to Session for path: {}", assetPath);
                }
            } else {
                LOGGER.warn("Resource not found at path: {}", assetPath);
            }
        } catch (Exception e) {
            LOGGER.error("Error while processing the job for asset publishing at path: {}", assetPath, e);
        }

        return JobResult.FAILED;
    }
}
