// package com.DailyTasks.core.servlets;

// import com.DailyTasks.core.service.ParentPathService;
// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;
// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.api.resource.ValueMap;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.ArgumentCaptor;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import javax.servlet.ServletException;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Arrays;
// import java.util.Iterator;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.*;

// class ParentPathServletTest {

//     @InjectMocks
//     private ParentPathServlet servlet;

//     @Mock
//     private ParentPathService parentPathService;

//     @Mock
//     private SlingHttpServletRequest request;

//     @Mock
//     private SlingHttpServletResponse response;

//     @Mock
//     private ResourceResolver resourceResolver;

//     @Mock
//     private Resource parentResource;

//     @Mock
//     private Resource childResource1;

//     @Mock
//     private Resource childResource2;

//     @Mock
//     private ValueMap childProperties1;

//     @Mock
//     private ValueMap childProperties2;

//     private PrintWriter writer;

//     @BeforeEach
//     void setUp() throws IOException {
//         // Initialize mocks manually
//         MockitoAnnotations.initMocks(this);

//         // Mock dependencies
//         when(request.getResourceResolver()).thenReturn(resourceResolver);
//         writer = mock(PrintWriter.class);
//         when(response.getWriter()).thenReturn(writer);
//     }

//     @Test
//     void testParentResourceNotFound() throws IOException, ServletException {
//         String parentPath = "/content/parent";
//         when(parentPathService.getPath()).thenReturn(parentPath);
//         when(resourceResolver.getResource(parentPath)).thenReturn(null);

//         servlet.doGet(request, response);

//         verify(response).setStatus(404);
//         verify(response).setContentType("application/json");
//         verify(writer).write("{\"error\": \"Parent resource not found at /content/parent\"}");
//     }

//     @SuppressWarnings("unchecked")
//     @Test
//     void testNoChildrenFound() throws IOException, ServletException {
//         String parentPath = "/content/parent";
//         when(parentPathService.getPath()).thenReturn(parentPath);
//         when(resourceResolver.getResource(parentPath)).thenReturn(parentResource);

//         when(parentResource.listChildren()).thenReturn(mock(Iterator.class));
//         when(parentResource.listChildren().hasNext()).thenReturn(false);

//         servlet.doGet(request, response);

//         verify(response).setStatus(404);
//         verify(response).setContentType("application/json");
//         verify(writer).write("{\"error\": \"No children found under /content/parent\"}");
//     }

//     @Test
//     void testChildrenFound() throws IOException, ServletException {
//         String parentPath = "/content/parent";
//         when(parentPathService.getPath()).thenReturn(parentPath);
//         when(resourceResolver.getResource(parentPath)).thenReturn(parentResource);

//         // Mocking child resources
//         Iterator<Resource> childIterator = Arrays.asList(childResource1, childResource2).iterator();
//         when(parentResource.listChildren()).thenReturn(childIterator);

//         when(childResource1.getPath()).thenReturn("/content/parent/child1");
//         when(childResource2.getPath()).thenReturn("/content/parent/child2");

//         when(childResource1.getValueMap()).thenReturn(childProperties1);
//         when(childResource2.getValueMap()).thenReturn(childProperties2);

//         when(childProperties1.get("jcr:primaryType", String.class)).thenReturn("cq:Page");
//         when(childProperties2.get("jcr:primaryType", String.class)).thenReturn("cq:Page");

//         servlet.doGet(request, response);

//         ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
//         verify(response).setContentType("application/json");
//         verify(writer).write(captor.capture());

//         String expectedJson = "{\"children\": [{\"path\": \"/content/parent/child1\"}, {\"path\": \"/content/parent/child2\"}]}";
//         assertEquals(expectedJson, captor.getValue());
//     }

//     @Test
//     void testChildrenNotOfTypePage() throws IOException, ServletException {
//         String parentPath = "/content/parent";
//         when(parentPathService.getPath()).thenReturn(parentPath);
//         when(resourceResolver.getResource(parentPath)).thenReturn(parentResource);

//         // Mocking non-page child resources
//         Iterator<Resource> childIterator = Arrays.asList(childResource1).iterator();
//         when(parentResource.listChildren()).thenReturn(childIterator);

//         when(childResource1.getValueMap()).thenReturn(childProperties1);
//         when(childProperties1.get("jcr:primaryType", String.class)).thenReturn("nt:folder");

//         servlet.doGet(request, response);

//         ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
//         verify(response).setContentType("application/json");
//         verify(writer).write(captor.capture());

//         String expectedJson = "{\"children\": []}";
//         assertEquals(expectedJson, captor.getValue());
//     }
// }
