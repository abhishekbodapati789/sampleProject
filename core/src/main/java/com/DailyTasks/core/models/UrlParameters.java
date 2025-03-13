package com.DailyTasks.core.models;

import org.apache.sling.api.resource.Resource;
import javax.inject.Inject;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import java.util.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UrlParameters {

    @Inject
    private String pathfield;

    public String getPathfield() {
        return Optional.ofNullable(pathfield).orElse("");
    }

    public Map<String, String> getQueryParams() {
        Map<String, String> paramsMap = new LinkedHashMap<>();

        if (pathfield != null && pathfield.contains("?")) {
            String[] urlParts = pathfield.split("\\?", 2);
            if (urlParts.length > 1) {
                String queryString = urlParts[1].split("#", 2)[0]; // Remove fragment (#)

                for (String param : queryString.split("&")) {
                    String[] keyValue = param.split("=", 2);
                    if (keyValue.length == 2) {
                        paramsMap.put(keyValue[0], keyValue[1]); // Key-value pair
                    } else {
                        paramsMap.put(keyValue[0], "N/A"); // Key with no value
                    }
                }
            }
        }

        return paramsMap;
    }

    // Helper method for HTL (AEM can't iterate over Maps directly)
    public List<Map.Entry<String, String>> getQueryParamsList() {
        return new ArrayList<>(getQueryParams().entrySet());
    }
}
