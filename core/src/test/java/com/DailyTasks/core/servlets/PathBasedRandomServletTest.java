// package com.DailyTasks.core.servlets;

// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.io.PrintWriter;
// import java.io.StringWriter;

// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.Mockito.*;

// class PathBasedRandomServletTest {

//     private PathBasedRandomServlet servlet;
//     private SlingHttpServletRequest request;
//     private SlingHttpServletResponse response;
//     private StringWriter responseWriter;

//     @BeforeEach
//     void setUp() throws Exception {
//         // Initialize the servlet manually
//         servlet = new PathBasedRandomServlet();

//         // Mock request and response
//         request = mock(SlingHttpServletRequest.class);
//         response = mock(SlingHttpServletResponse.class);

//         // Setup response writer to capture output
//         responseWriter = new StringWriter();
//         PrintWriter writer = new PrintWriter(responseWriter);
//         when(response.getWriter()).thenReturn(writer);
//     }

//     @Test
//     void testDoGetWithSessionParam() throws Exception {
//         when(request.getParameter("session")).thenReturn("true");

//         servlet.doGet(request, response);

//         verify(response).setContentType("text/plain");
//         verify(response).setCharacterEncoding("UTF-8");

//         String responseOutput = responseWriter.toString();
//         assertTrue(responseOutput.contains("Generated session word:"));
//         assertTrue(responseOutput.split(" ").length > 3);
//     }

//     @Test
//     void testDoGetWithRandomParam() throws Exception {
//         when(request.getParameter("random")).thenReturn("true");

//         servlet.doGet(request, response);

//         verify(response).setContentType("text/plain");
//         verify(response).setCharacterEncoding("UTF-8");

//         String responseOutput = responseWriter.toString();
//         assertTrue(responseOutput.contains("Generated random numbers:"));
//         assertTrue(responseOutput.matches(".*Generated random numbers: \\d{6}.*"));
//     }

//     @Test
//     void testDoGetWithNoValidParam() throws Exception {
//         servlet.doGet(request, response);

//         verify(response).setContentType("text/plain");
//         verify(response).setCharacterEncoding("UTF-8");

//         String responseOutput = responseWriter.toString();
//         assertTrue(responseOutput.contains("No valid parameter provided. Use 'session' or 'random'."));
//     }

//     @Test
//     void testGenerateRandomWord() {
//         String randomWord = servlet.generateRandomWord();
//         assertTrue(randomWord.matches("[a-zA-Z]{6}"));
//     }

//     @Test
//     void testGenerateRandomNumbers() {
//         String randomNumbers = servlet.generateRandomNumbers();
//         assertTrue(randomNumbers.matches("\\d{6}"));
//     }
// }
