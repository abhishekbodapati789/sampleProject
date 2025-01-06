// package com.DailyTasks.core.servlets;

// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;
// import org.apache.sling.api.resource.Resource;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.io.IOException;
// import java.io.PrintWriter;
// import java.io.StringWriter;

// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.Mockito.*;

// class ResourceBasedJsonServletTest {

//     private ResourceBasedJsonServlet servlet;
//     private SlingHttpServletRequest request;
//     private SlingHttpServletResponse response;
//     private Resource resource;
//     private StringWriter responseWriter;

//     @BeforeEach
//     void setUp() throws Exception {
//         // Initialize the servlet manually
//         servlet = new ResourceBasedJsonServlet();

//         // Mock request, response, and resource
//         request = mock(SlingHttpServletRequest.class);
//         response = mock(SlingHttpServletResponse.class);
//         resource = mock(Resource.class);

//         // Setup response writer to capture output
//         responseWriter = new StringWriter();
//         PrintWriter writer = new PrintWriter(responseWriter);
//         when(response.getWriter()).thenReturn(writer);
//     }

//     @Test
//     void testDoGetWithValidResource() throws Exception {
//         // Mock resource properties
//         when(request.getResource()).thenReturn(resource);
//         when(resource.getPath()).thenReturn("/content/sample/path");
//         when(resource.getName()).thenReturn("sampleResource");

//         // Call the servlet
//         servlet.doGet(request, response);

//         // Verify response properties
//         verify(response).setContentType("application/json");
//         verify(response).setCharacterEncoding("UTF-8");

//         // Capture the response output
//         String responseOutput = responseWriter.toString();

//         // Validate the JSON response
//         assertTrue(responseOutput.contains("\"message\":\"Hello, this is my first resource-based servlet.\""));
//         assertTrue(responseOutput.contains("\"path\":\"/content/sample/path\""));
//         assertTrue(responseOutput.contains("\"name\":\"sampleResource\""));
//     }

//     @Test
//     void testDoGetWithNullResource() throws Exception {
//         // Mock a null resource
//         when(request.getResource()).thenReturn(null);

//         // Call the servlet
//         servlet.doGet(request, response);

//         // Verify response properties
//         verify(response).setContentType("application/json");
//         verify(response).setCharacterEncoding("UTF-8");
//         verify(response).setStatus(SlingHttpServletResponse.SC_NOT_FOUND);

//         // Capture the response output
//         String responseOutput = responseWriter.toString();

//         // Validate the error JSON response
//         assertTrue(responseOutput.contains("\"error\":\"Resource not found\""));
//     }

//     @Test
//     void testDoGetWithMalformedResponseWriter() throws Exception {
//         // Mock resource properties
//         when(request.getResource()).thenReturn(resource);
//         when(resource.getPath()).thenReturn("/content/sample/path");
//         when(resource.getName()).thenReturn("sampleResource");

//         // Simulate an exception when trying to get the writer
//         when(response.getWriter()).thenThrow(new IOException("Simulated IOException"));

//         // Call the servlet
//         try {
//             servlet.doGet(request, response);
//         } catch (IOException e) {
//             // Verify that the exception is logged or thrown correctly
//             assertTrue(e.getMessage().contains("Simulated IOException"));
//         }
//     }

//     @Test
//     void testDoGetWithEmptyResponse() throws Exception {
//         // Mock resource properties but don't set path or name
//         when(request.getResource()).thenReturn(resource);
//         when(resource.getPath()).thenReturn(null);
//         when(resource.getName()).thenReturn(null);

//         // Call the servlet
//         servlet.doGet(request, response);

//         // Capture the response output
//         String responseOutput = responseWriter.toString();

//         // Validate that it handles empty resource details correctly
//         assertTrue(responseOutput.contains("\"message\":\"Hello, this is my first resource-based servlet.\""));
//         assertTrue(responseOutput.contains("\"path\":null"));
//         assertTrue(responseOutput.contains("\"name\":null"));
//     }
// }
