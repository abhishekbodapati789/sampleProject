package com.DailyTasks.core.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class,
property = {"sling.servlet.paths=/bin/abhishek"})
public class Mockintpathbased extends SlingSafeMethodsServlet {
   @Override
   protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response)throws IOException{
response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");


String message;
message=("hello happy christmas !!");
response.getWriter().write(message);


   }

}

