// package com.DailyTasks.core.servlets;

// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;
// import org.apache.sling.api.servlets.SlingAllMethodsServlet;
// import org.osgi.service.component.annotations.Component;
// import javax.servlet.Servlet;
// import java.io.IOException;

// @Component(service = Servlet.class,property = {
    
//     "sling.servlet.resourceTypes=" + "DailyTasks/components/helloworld",
//      "sling.servlet.extensions=" + "json"  })
// public class helloworld extends SlingAllMethodsServlet {

//     @Override
//     protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
//         response.setContentType("text/plain");
//         response.setCharacterEncoding("UTF-8");
//         response.getWriter().write("Hello, World! This is a path-based servlet in AEM.",resource.path);
//     }
// }
