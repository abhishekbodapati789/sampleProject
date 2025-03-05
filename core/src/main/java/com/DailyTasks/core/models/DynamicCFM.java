package com.DailyTasks.core.models;

import com.adobe.cq.dam.cfm.ContentFragment;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class DynamicCFM {

    private static final Logger LOG = LoggerFactory.getLogger(DynamicCFM.class);

    @ValueMapValue
    private String path; // Path to the Content Fragment

    @Inject
    private ResourceResolver resourceResolver;

    /**
     * Fetch the value for a specific property (element) from the Content Fragment.
     *
     * @param propertyName The name of the property to retrieve from the Content Fragment
     * @return The content value or null if the property is not found
     */
    public String getContentFragmentProperty(String propertyName) {
        try {
            if (path != null) {
                // Resolve the Content Fragment resource based on the provided path
                Resource contentFragmentResource = resourceResolver.getResource(path);

                if (contentFragmentResource != null) {
                    // Adapt the resource to a ContentFragment
                    ContentFragment contentFragment = contentFragmentResource.adaptTo(ContentFragment.class);

                    if (contentFragment != null && contentFragment.hasElement(propertyName)) {
                        return contentFragment.getElement(propertyName).getContent();
                    } else {
                        LOG.warn("Content Fragment does not contain the element: {}", propertyName);
                    }
                } else {
                    LOG.warn("No resource found at path: {}", path);
                }
            } else {
                LOG.warn("Path to Content Fragment is null");
            }
        } catch (Exception e) {
            LOG.error("Error while fetching Content Fragment property", e);
        }
        return null;
    }

    /**
     * Get the Content Fragment path from the component dialog.
     *
     * @return the Content Fragment path
     */
    public String getPath() {
        return path;
    }
}
