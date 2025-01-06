// package com.DailyTasks.core.models;

// import io.wcm.testing.mock.aem.junit5.AemContext;
// import io.wcm.testing.mock.aem.junit5.AemContextExtension;
// import org.apache.sling.api.resource.Resource;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;

// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;

// /**
//  * Test class for Task3 model.
//  */
// @ExtendWith(AemContextExtension.class)
// class Task3Test {

//     private final AemContext context = new AemContext();

//     private Task3 task3;

//     @BeforeEach
//     void setUp() {
//         // Create the resource at /content/task3 with all fields
//         Resource resource = context.create().resource("/content/task3",
//                 "path", "/content/page1",
//                 "link", "http://example.com",
//                 "checkbox", true,
//                 "country", "USA");

//         // Create child resources for multifield and multi
//         context.create().resource(resource, "multiField1", "fname", "John", "image", "/images/john.png");
//         context.create().resource(resource, "multiField2", "fname", "Jane", "image", "/images/jane.png");

//         context.create().resource(resource, "multi1", "desktopIcon", "desktop1.png", "mobileIcon", "mobile1.png");
//         context.create().resource(resource, "multi2", "desktopIcon", "desktop2.png", "mobileIcon", "mobile2.png");

//         // Create nested multi resources
//         context.create().resource(resource, "multi1/nested1", "NavigationURL", "http://nested1.com");
//         context.create().resource(resource, "multi2/nested2", "NavigationURL", "http://nested2.com");

//         // Adapt the resource to Task3
//         task3 = resource.adaptTo(Task3.class);
//     }

//     @Test
//     void testGetPath() {
//         assertNotNull(task3, "Task3 should not be null");
//         assertEquals("/content/page1", task3.getPath(), "Path value mismatch");
//     }

//     @Test
//     void testGetLink() {
//         assertNotNull(task3, "Task3 should not be null");
//         assertEquals("http://example.com", task3.getLink(), "Link value mismatch");
//     }

//     @Test
//     void testGetCheckbox() {
//         assertNotNull(task3, "Task3 should not be null");
//         assertEquals(true, task3.getCheckbox(), "Checkbox value mismatch");
//     }

//     @Test
//     void testGetMultifield() {
//         List<Task3.Multi> multifield = task3.getMultifield();
//         assertNotNull(multifield, "Multifield should not be null");
//         assertEquals(2, multifield.size(), "Multifield size mismatch");
//         assertEquals("John", multifield.get(0).getFname(), "First field fname mismatch");
//         assertEquals("/images/john.png", multifield.get(0).getImage(), "First field image mismatch");
//         assertEquals("Jane", multifield.get(1).getFname(), "Second field fname mismatch");
//         assertEquals("/images/jane.png", multifield.get(1).getImage(), "Second field image mismatch");
//     }

//     @Test
//     void testGetMulti() {
//         List<Task3.Multifield> multi = task3.getMulti();
//         assertNotNull(multi, "Multi should not be null");
//         assertEquals(2, multi.size(), "Multi size mismatch");

//         Task3.Multifield firstMultifield = multi.get(0);
//         assertEquals("desktop1.png", firstMultifield.getDesktopIcon(), "First multi desktop icon mismatch");
//         assertEquals("mobile1.png", firstMultifield.getMobileIcon(), "First multi mobile icon mismatch");

//         Task3.Multifield secondMultifield = multi.get(1);
//         assertEquals("desktop2.png", secondMultifield.getDesktopIcon(), "Second multi desktop icon mismatch");
//         assertEquals("mobile2.png", secondMultifield.getMobileIcon(), "Second multi mobile icon mismatch");

//         // Check nested multi fields
//         assertNotNull(firstMultifield.getNestedMulti(), "First multi nested should not be null");
//         assertEquals(1, firstMultifield.getNestedMulti().size(), "First multi nested size mismatch");
//         assertEquals("http://nested1.com", firstMultifield.getNestedMulti().get(0).getNavigationURL(), "First nested URL mismatch");

//         assertNotNull(secondMultifield.getNestedMulti(), "Second multi nested should not be null");
//         assertEquals(1, secondMultifield.getNestedMulti().size(), "Second multi nested size mismatch");
//         assertEquals("http://nested2.com", secondMultifield.getNestedMulti().get(0).getNavigationURL(), "Second nested URL mismatch");
//     }

//     @Test
//     void testGetCountry() {
//         assertNotNull(task3, "Task3 should not be null");
//         assertEquals("USA", task3.getCountry(), "Country value mismatch");
//     }

//     @Test
//     void testMultifieldWhenEmpty() {
//         // Create a resource with no multifield data
//         Resource resource = context.create().resource("/content/task3_empty", "path", "/content/page2");
//         task3 = resource.adaptTo(Task3.class);

//         assertNotNull(task3, "Task3 should not be null");
//         assertNull(task3.getMultifield(), "Multifield should be null when not provided");
//     }

//     @Test
//     void testMultiWhenEmpty() {
//         // Create a resource with no multi data
//         Resource resource = context.create().resource("/content/task3_empty", "path", "/content/page2");
//         task3 = resource.adaptTo(Task3.class);

//         assertNotNull(task3, "Task3 should not be null");
//         assertNull(task3.getMulti(), "Multi should be null when not provided");
//     }
// }
