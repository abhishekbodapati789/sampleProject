package com.DailyTasks.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.google.gson.JsonObject;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {

                "sling.servlet.paths=/bin/helo"
        })
public class Demoservlet1 extends SlingAllMethodsServlet {



    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        JsonObject j=new JsonObject();
        j.addProperty("kkjjj","gdedewfuerfe");
        j.addProperty("message", "Hello this is my first resource based servlet code.");

        response.getWriter().write(j.toString());


    }
}
