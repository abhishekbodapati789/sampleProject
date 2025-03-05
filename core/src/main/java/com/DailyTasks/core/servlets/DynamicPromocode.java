package com.DailyTasks.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.google.gson.Gson;

@SuppressWarnings("serial")
@Component(service = Servlet.class, property = {
    "sling.servlet.resourceTypes=/apps/dropdown/data/abhishek11",
    "sling.servlet.methods=GET"
})
public class DynamicPromocode extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DynamicPromocode.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        try {
            ResourceResolver resolver = request.getResourceResolver();
            Resource contentFragmentResource = resolver.getResource("/content/dam/DailyTasks/dynamiccf11");

            if (contentFragmentResource == null) {
                LOG.error("Content Fragment not found: /content/dam/DailyTasks/dynamiccf11");
                response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Content Fragment not found\"}");
                return;
            }

            // Fetch CF properties (actual data fields)
            ValueMap properties = contentFragmentResource.getValueMap();
            Map<String, String> data = new TreeMap<>();

            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                
                // Store CF field name and value
                data.put(key, value);
            }

            // Check if request expects JSON
            String format = request.getParameter("format");
            if ("json".equalsIgnoreCase(format)) {
                Gson gson = new Gson();
                String jsonData = gson.toJson(data);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonData);
            } else {
                // Populate dropdown with CF field names
                DataSource ds = new SimpleDataSource(new TransformIterator<>(data.keySet().iterator(),
                        (Transformer<String, ValueMapResource>) fieldName -> {
                            ValueMap vm = new ValueMapDecorator(new HashMap<>());
                            vm.put("text", fieldName);
                            vm.put("value", data.get(fieldName)); // Field value
                            return new ValueMapResource(resolver, new ResourceMetadata(), "nt:unstructured", vm);
                        }));

                request.setAttribute(DataSource.class.getName(), ds);
            }
        } catch (Exception e) {
            LOG.error("Error while fetching the Content Fragment data", e);
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Internal server error\"}");
        }
    }
}
