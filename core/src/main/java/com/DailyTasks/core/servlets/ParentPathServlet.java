package com.DailyTasks.core.servlets;

import com.DailyTasks.core.service.ParentPathService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Iterator;

@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.paths=/bin/servlet/childpaths",
        "sling.servlet.methods=GET"
    }
)
public class ParentPathServlet extends SlingAllMethodsServlet {

    @Reference
    private ParentPathService parentPathService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String parentPath =parentPathService.getPath();
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource parentResource = resourceResolver.getResource(parentPath);

        if (parentResource == null) {
            response.setStatus(404);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Parent resource not found at " + parentPath + "\"}");
            return;
        }

        Iterator<Resource> children = parentResource.listChildren();
        if (!children.hasNext()) {
            response.setStatus(404);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"No children found under " + parentPath + "\"}");
            return;
        }

        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("{\"children\": [");

        boolean first = true;

        while (children.hasNext()) {
            Resource child = children.next();
            ValueMap properties = child.getValueMap();
            if ("cq:Page".equals(properties.get("jcr:primaryType", String.class))) {
                if (!first) {
                    jsonResponse.append(", ");
                }
                String childPath = child.getPath();
                jsonResponse.append("{\"path\": \"").append(childPath).append("\"}");
                first = false;
            }
        }

        jsonResponse.append("]}");

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
