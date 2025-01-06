package com.DailyTasks.core.servlets;

import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.resourceTypes=DailyTasks/components/Task1", // Resource Type
                "sling.servlet.extensions=json",                               // Handles .json requests
                "sling.servlet.methods=GET"                                    // Only respond to GET requests
        })
public class ResourceBasedJsonServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Get the current resource
        Resource resource = request.getResource();

        if (resource != null) {
            // Create a JSON response
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("message", "Hello, this is my first resource-based servlet.");
            jsonResponse.addProperty("path", resource.getPath());
            jsonResponse.addProperty("name", resource.getName());
            response.getWriter().write(jsonResponse.toString());
        } else {
            // Handle case where the resource is null
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("error", "Resource not found");
            response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(errorResponse.toString());
        }
    }
}
