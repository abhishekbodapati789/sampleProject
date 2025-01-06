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

// @ExtendWith(AemContextExtension.class)
// class Task2Test {

//     private final AemContext context = new AemContext();

//     private Task2 task2;

//     @BeforeEach
//     void setUp() {
//         // Create the resource at /content/task2 with textfield value
//         Resource resource = context.create().resource("/content/task2", "textfield", "Sample Text");

//         // Create child resources under /content/task2 for multiFieldData
//         Resource child1 = context.create().resource(resource, "child1",
//                 "plaintext", "Plain Text 1",
//                 "fileupload", "/content/dam/file1.png",
//                 "checkbox", "true",
//                 "dropdown", "Option1");

//         Resource child2 = context.create().resource(resource, "child2",
//                 "plaintext", "Plain Text 2",
//                 "fileupload", "/content/dam/file2.png",
//                 "checkbox", "false",
//                 "dropdown", "Option2");

//         // Create nested child resources for each child
//         context.create().resource(child1, "nested1", "richtext", "Nested Rich Text 1");
//         context.create().resource(child2, "nested2", "richtext", "Nested Rich Text 2");

//         // Adapt the resource to Task2
//         task2 = resource.adaptTo(Task2.class);
//     }

//     @Test
//     void testGetTextfield() {
//         assertNotNull(task2, "Task2 should not be null");
//         assertEquals("Sample Text", task2.getTextfield(), "Textfield value mismatch");
//     }

//     @Test
//     void testGetMultiFieldData() {
//         List<Task2.Child> multiFieldData = task2.getMultiFieldData();
//         assertNotNull(multiFieldData, "MultiFieldData should not be null");
//         assertEquals(2, multiFieldData.size(), "MultiFieldData size mismatch");

//         // Test the first child
//         Task2.Child child1 = multiFieldData.get(0);
//         assertEquals("Plain Text 1", child1.getPlaintext(), "First child plaintext mismatch");
//         assertEquals("/content/dam/file1.png", child1.getFileupload(), "First child fileupload mismatch");
//         assertEquals("true", child1.getCheckbox(), "First child checkbox mismatch");
//         assertEquals("Option1", child1.getDropdown(), "First child dropdown mismatch");

//         // Verify nested child for the first child
//         List<Task2.Child.NestedChild> nested1 = child1.getNestedFieldData();
//         assertNotNull(nested1, "Nested field data should not be null for child 1");
//         assertEquals(1, nested1.size(), "Nested field size mismatch for child 1");
//         assertEquals("Nested Rich Text 1", nested1.get(0).getRichtext(), "First nested child richtext mismatch");

//         // Test the second child
//         Task2.Child child2 = multiFieldData.get(1);
//         assertEquals("Plain Text 2", child2.getPlaintext(), "Second child plaintext mismatch");
//         assertEquals("/content/dam/file2.png", child2.getFileupload(), "Second child fileupload mismatch");
//         assertEquals("false", child2.getCheckbox(), "Second child checkbox mismatch");
//         assertEquals("Option2", child2.getDropdown(), "Second child dropdown mismatch");

//         // Verify nested child for the second child
//         List<Task2.Child.NestedChild> nested2 = child2.getNestedFieldData();
//         assertNotNull(nested2, "Nested field data should not be null for child 2");
//         assertEquals(1, nested2.size(), "Nested field size mismatch for child 2");
//         assertEquals("Nested Rich Text 2", nested2.get(0).getRichtext(), "Second nested child richtext mismatch");
//     }

//     @Test
//     void testMultiFieldDataWhenEmpty() {
//         // Create a resource with no multiFieldData
//         Resource resource = context.create().resource("/content/task2_empty", "textfield", "Sample Text");
//         task2 = resource.adaptTo(Task2.class);

//         assertNotNull(task2, "Task2 should not be null");
//         assertNull(task2.getMultiFieldData(), "MultiFieldData should be null when not provided");
//     }
// }
