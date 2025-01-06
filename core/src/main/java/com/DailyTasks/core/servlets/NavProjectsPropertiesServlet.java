// package com.DailyTasks.core.servlets;

// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;
// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.api.servlets.SlingAllMethodsServlet;
// import org.osgi.service.component.annotations.Component;
// import org.osgi.service.component.propertytypes.ServiceDescription;
// import org.osgi.service.component.propertytypes.ServiceVendor;

// import javax.jcr.Node;
// import javax.jcr.Property;
// import javax.jcr.PropertyIterator;
// import javax.jcr.RepositoryException;
// import javax.servlet.Servlet;
// import java.io.IOException;

// @Component(
//     service = Servlet.class,
//     property = {
//         "sling.servlet.methods=GET",
//         "sling.servlet.paths=/bin/mahesh/abhi/nodeproperties",
//         "sling.servlet.extensions=json"
//     }
// )
// @ServiceDescription("Servlet to print JCR properties of /libs/cq/core/content/nav/projects node")
// @ServiceVendor("Example Company")
// public class NavProjectsPropertiesServlet extends SlingAllMethodsServlet {

//     @Override
//     protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
//         // Get the Resource Resolver
//         ResourceResolver resourceResolver = request.getResourceResolver();

//         // Define the node path
//         String nodePath = "/apps/DailyTasks/components/page";

//         try {
//             // Get the Resource and JCR Node
//             Resource resource = resourceResolver.getResource(nodePath);
//             if (resource == null) {
//                 response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
//                 response.getWriter().write("{\"error\": \"Node not found at path: " + nodePath + "\"}");
//                 return;
//             }

//             Node node = resource.adaptTo(Node.class);
//             if (node == null) {
//                 response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
//                 response.getWriter().write("{\"error\": \"Could not adapt resource to JCR Node.\"}");
//                 return;
//             }

//             // Prepare JSON output
//             response.setContentType("application/json");
//             response.setCharacterEncoding("UTF-8");
//             StringBuilder jsonOutput = new StringBuilder("{");


//             PropertyIterator properties = node.getProperties();
//             while (properties.hasNext()) {
//                 Property property = properties.nextProperty();
//                 String name = property.getName();
//                 String value;
//                 if (property.isMultiple()) {
//                     StringBuilder multiValue = new StringBuilder("[");
//                     for (javax.jcr.Value val : property.getValues()) {
//                         multiValue.append("\"").append(val.getString()).append("\",");
//                     }
//                     if (multiValue.length() > 1) {
//                         multiValue.setLength(multiValue.length() - 1); // Remove trailing comma
//                     }
//                     multiValue.append("]");
//                     value = multiValue.toString();
//                 } else {
//                     value = "\"" + property.getString() + "\"";
//                 }

//                 // Append to JSON output
//                 jsonOutput.append("\"").append(name).append("\": ").append(value).append(",");
//             }

//             // Remove trailing comma and close JSON object
//             if (jsonOutput.length() > 1) {
//                 jsonOutput.setLength(jsonOutput.length() - 1);
//             }
//             jsonOutput.append("}");

//             response.getWriter().write(jsonOutput.toString());

//         } catch (RepositoryException e) {
//             response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//             response.getWriter().write("{\"error\": \"Error while accessing JCR properties: " + e.getMessage() + "\"}");
//         }
//     }
// }
