// package com.DailyTasks.core.models;

// import io.wcm.testing.mock.aem.junit5.AemContext;
// import io.wcm.testing.mock.aem.junit5.AemContextExtension;
// import org.apache.sling.api.resource.Resource;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// /**
//  * Test class for Task5 model.
//  */
// @ExtendWith(AemContextExtension.class)
// class Task5Test {

//     private final AemContext context = new AemContext();

//     private Task5 task5;

//     @BeforeEach
//     void setUp() {
//         // Create the resource at /content/task5 with a checkbox and textfield
//         Resource resource = context.create().resource("/content/task5",
//                 "checkbox", "true",
//                 "textfield", "Sample Text");

//         // Adapt the resource to the Task5 model
//         task5 = resource.adaptTo(Task5.class);
//     }

//     @Test
//     void testGetCheckbox() {
//         assertNotNull(task5, "Task5 should not be null");
//         assertEquals("true", task5.isCheckbox(), "Checkbox value mismatch");
//     }

//     @Test
//     void testGetTextfield() {
//         assertNotNull(task5, "Task5 should not be null");
//         assertEquals("Sample Text", task5.getTextfield(), "Textfield value mismatch");
//     }

//     @Test
//     void testGetTextfieldWithDefault() {
//         // Create resource with no textfield value
//         Resource resource = context.create().resource("/content/task5_with_default");
//         task5 = resource.adaptTo(Task5.class);

//         assertNotNull(task5, "Task5 should not be null");
//         assertEquals("", task5.getTextfield(), "Textfield default value mismatch");
//     }

//     @Test
//     void testGetCheckboxWhenCheckboxIsNull() {
//         // Create resource with no checkbox value
//         Resource resource = context.create().resource("/content/task5_no_checkbox", "textfield", "Sample Text");
//         task5 = resource.adaptTo(Task5.class);

//         assertNotNull(task5, "Task5 should not be null");
//         assertEquals(null, task5.isCheckbox(), "Checkbox value should be null when not provided");
//     }
// }
