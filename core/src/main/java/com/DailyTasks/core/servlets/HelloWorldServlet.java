package com.DailyTasks.core.servlets;


import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;

import java.io.IOException;
import java.io.PrintWriter;

@Component(
    service = javax.servlet.Servlet.class,
    immediate = true,
    property = {
        Constants.SERVICE_DESCRIPTION + "=HelloWorld Servlet",
        "sling.servlet.methods=GET",
        "sling.servlet.paths=/bin/hello"
    }
)
public class HelloWorldServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        // Set the response content type
        response.setContentType("text/html");

        // Write the response
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello, World!</h1>");
        out.println("</body></html>");
    }
}
