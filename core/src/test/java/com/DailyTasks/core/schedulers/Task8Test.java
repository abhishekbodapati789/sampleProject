// package com.DailyTasks.core.schedulers;

// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.api.resource.ResourceResolverFactory;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.slf4j.Logger;

// import java.util.Collections;
// import java.util.Iterator;

// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// class Task8Test {

//     private static final String PATH_TO_PUBLISH = "/content/DailyTasks/us/en";

//     @Mock
//     private ResourceResolverFactory resourceResolverFactory;

//     @Mock
//     private ResourceResolver resourceResolver;

//     @Mock
//     private Resource parentResource;

//     @Mock
//     private Resource childResource;

//     @Mock
//     private Logger logger;

//     @InjectMocks
//     private Task8 task8;

//     @BeforeEach
//     void setUp() {
//         task8 = new Task8();
//     }

//     @Test
//     void testRun_Success() throws Exception {
//         // Mock the resource resolver
//         when(resourceResolverFactory.getServiceResourceResolver(Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "abhi")))
//                 .thenReturn(resourceResolver);

//         // Mock the parent resource
//         when(resourceResolver.getResource(PATH_TO_PUBLISH)).thenReturn(parentResource);

//         // Mock child resources
//         @SuppressWarnings("unchecked")
//         Iterator<Resource> childIterator = mock(Iterator.class);
//         when(parentResource.listChildren()).thenReturn(childIterator);
//         when(childIterator.hasNext()).thenReturn(true, false);
//         when(childIterator.next()).thenReturn(childResource);
//         when(childResource.getPath()).thenReturn("/content/DailyTasks/us/en/child1");

//         // Run the task
//         task8.run();

//         // Verify interactions
//         verify(logger, times(1)).info("Executing scheduled task to publish child pages under {}", PATH_TO_PUBLISH);
//         verify(logger, times(1)).info("Publishing page: {}", "/content/DailyTasks/us/en/child1");
//         verify(resourceResolver, times(1)).close();
//     }

//     @Test
//     void testRun_ParentResourceNotFound() throws Exception {
//         // Mock the resource resolver
//         when(resourceResolverFactory.getServiceResourceResolver(Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "abhi")))
//                 .thenReturn(resourceResolver);

//         // Mock parent resource as null
//         when(resourceResolver.getResource(PATH_TO_PUBLISH)).thenReturn(null);

//         // Run the task
//         task8.run();

//         // Verify interactions
//         verify(logger, times(1)).warn("Parent resource not found at path: {}", PATH_TO_PUBLISH);
//         verify(resourceResolver, times(1)).close();
//     }

//     @Test
//     void testRun_ResourceResolverNull() throws Exception {
//         // Mock the resource resolver factory to return null
//         when(resourceResolverFactory.getServiceResourceResolver(Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "abhi")))
//                 .thenThrow(new RuntimeException("Failed to obtain ResourceResolver"));

//         // Run the task
//         task8.run();

//         // Verify interactions
//         verify(logger, times(1)).error("Failed to obtain ResourceResolver.");
//     }

//     @Test
//     void testRun_ExceptionWhilePublishing() throws Exception {
//         // Mock the resource resolver
//         when(resourceResolverFactory.getServiceResourceResolver(Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "abhi")))
//                 .thenReturn(resourceResolver);

//         // Mock the parent resource
//         when(resourceResolver.getResource(PATH_TO_PUBLISH)).thenReturn(parentResource);

//         // Mock child resources and throw exception
//         when(parentResource.listChildren()).thenThrow(new RuntimeException("Error while iterating children"));

//         // Run the task
//         task8.run();

//         // Verify interactions
//         verify(logger, times(1)).error(eq("Error while publishing pages"), any(RuntimeException.class));
//         verify(resourceResolver, times(1)).close();
//     }
// }
