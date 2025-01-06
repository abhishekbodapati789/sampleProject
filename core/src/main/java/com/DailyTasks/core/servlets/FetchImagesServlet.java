package com.DailyTasks.core.servlets;

import com.day.cq.dam.api.Asset;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.methods=GET",
                "sling.servlet.paths=/bin/fetchImages" // Servlet path
        }
)
public class FetchImagesServlet extends SlingSafeMethodsServlet {

    private static final String SERVICE_USER = "abhi"; // Replace with your service user

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String folderPath = "/content/dam/DailyTasks/images"; // DAM folder path

        try (ResourceResolver resourceResolver = getResourceResolver()) {
            Resource folderResource = resourceResolver.getResource(folderPath);
            if (folderResource == null) {
                response.getWriter().write("[]"); // Return empty JSON if folder doesn't exist
                return;
            }

            JsonArrayBuilder jsonArray = Json.createArrayBuilder();
            Iterator<Resource> resourceIterator = folderResource.listChildren();

            while (resourceIterator.hasNext()) {
                Resource childResource = resourceIterator.next();
                Asset asset = childResource.adaptTo(Asset.class);

                if (asset != null) {
                    jsonArray.add(Json.createObjectBuilder()
                            .add("value", asset.getPath()) // Image path for rendering
                            .add("text", asset.getName())  // Image name for dropdown
                            .build());
                }
            }

            response.getWriter().write(jsonArray.build().toString());
        } catch (LoginException e) {
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Unable to access the resource resolver\"}");
        }
    }

    private ResourceResolver getResourceResolver() throws LoginException {
        Map<String, Object> serviceParams = new HashMap<>();
        serviceParams.put(ResourceResolverFactory.SUBSERVICE, SERVICE_USER);
        return resolverFactory.getServiceResourceResolver(serviceParams);
    }
}
