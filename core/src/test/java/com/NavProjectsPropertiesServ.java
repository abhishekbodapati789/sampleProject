// package com;

// import com.DailyTasks.core.servlets.NavProjectsPropertiesServlet; // Ensure you import the correct servlet class
// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;
// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import javax.jcr.Node;
// import javax.jcr.Property;
// import javax.jcr.PropertyIterator;
// import javax.jcr.RepositoryException;
// import java.io.PrintWriter;
// import java.io.StringWriter;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.*;

// class NavProjectsPropertiesServletTest {

//     private NavProjectsPropertiesServlet servlet; // Correctly reference the servlet to test

//     @Mock
//     private SlingHttpServletRequest request;

//     @Mock
//     private SlingHttpServletResponse response;

//     @Mock
//     private ResourceResolver resourceResolver;

//     @Mock
//     private Resource resource;

//     @Mock
//     private Node node;

//     @Mock
//     private PropertyIterator propertyIterator;

//     @Mock
//     private Property property;

//     private StringWriter responseWriter;

//     @BeforeEach
//     void setUp() throws Exception {
//         MockitoAnnotations.openMocks(this);
//         servlet = new NavProjectsPropertiesServlet();

//         // Set up response writer
//         responseWriter = new StringWriter();
//         when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

//         // Mock request to return a ResourceResolver
//         when(request.getResourceResolver()).thenReturn(resourceResolver);
//     }

//     @Test
//     void testNodeFoundWithProperties() throws Exception {}
//         //
