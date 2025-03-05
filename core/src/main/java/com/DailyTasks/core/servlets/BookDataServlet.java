package com.DailyTasks.core.servlets;

import com.day.cq.commons.jcr.JcrUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;

@Component(
        service = {Servlet.class},
        immediate = true
)
@SlingServletPaths("/bin/storebooks")
public class BookDataServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            Session session = request.getResourceResolver().adaptTo(Session.class);
            if (session == null) {
                response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"error\":\"Could not adapt to JCR session\"}");
                return;
            }

            String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(requestBody);

            if (!jsonElement.isJsonArray()) {
                response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"error\":\"Invalid JSON format\"}");
                return;
            }

            JsonArray jsonArray = jsonElement.getAsJsonArray();
            Node damNode = session.getNode("/content/dam");
            Node booksNode = JcrUtil.createPath(damNode.getPath() + "/books", "nt:unstructured", session);

            for (JsonElement element : jsonArray) {
                if (element.isJsonObject()) {
                    JsonObject bookObject = element.getAsJsonObject();
                    String bookName = bookObject.has("bookName") ? bookObject.get("bookName").getAsString() : "Unknown";
                    String authorName = bookObject.has("authorName") ? bookObject.get("authorName").getAsString() : "Unknown";

                    Node bookNode = JcrUtil.createUniqueNode(booksNode, "book", "nt:unstructured", session);
                    bookNode.setProperty("bookName", bookName);
                    bookNode.setProperty("authorName", authorName);
                }
            }
            session.save();
            response.setStatus(SlingHttpServletResponse.SC_OK);
            out.write("{\"message\":\"Books stored successfully\"}");
        } catch (RepositoryException e) {
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"error\":\"Repository error: " + e.getMessage() + "\"}");
        }
    }
}
