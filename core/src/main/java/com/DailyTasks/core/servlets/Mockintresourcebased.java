package com.DailyTasks.core.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;

import com.google.gson.JsonObject;

@Component(service = Servlet.class,
property = {
    "sling.servlet.resourceTypes=DailyTasks/components/Task1",
    "sling.servlet.extensions=json",
    "sling.servlet.methods=GET"
})
public class Mockintresourcebased extends SlingSafeMethodsServlet {

@Override
protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response)throws IOException{
   response.setContentType("application/json");
   response.setCharacterEncoding("UTF-8");


   Resource resource=request.getResource();
JsonObject j=new JsonObject();
j.addProperty("Message", "hello!!");
j.addProperty("Path",resource.getPath());
response.getWriter().write(j.toString());
}

}
