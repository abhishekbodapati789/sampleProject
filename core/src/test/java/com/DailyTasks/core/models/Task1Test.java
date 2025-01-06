// package com.DailyTasks.core.models;

// import org.apache.sling.api.resource.Resource;
// import io.wcm.testing.mock.aem.junit5.AemContext;
// import io.wcm.testing.mock.aem.junit5.AemContextExtension;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;

// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// /**
//  * Test class for Task1 model.
//  */
// @ExtendWith(AemContextExtension.class)
// class Task1Test {

//     private final AemContext context = new AemContext();
//     private Task1 task1;

//     @BeforeEach
//     void setUp() {
//         // Create the resource at /content/task1
//         Resource resource = context.create().resource("/content/task1",
//                 "textfield", "Sample Text",
//                 "fileupload", "/content/dam/sample.png",
//                 "checkbox", "true",
//                 "dropdown", "Option1");

//         // Create child resources (child1 and child2) under /content/task1
//         context.create().resource(resource, "child1", "richtext", "Child 1 Text");
//         context.create().resource(resource, "child2", "richtext", "Child 2 Text");

//         // Adapt the resource to the Task1 model
//         task1 = resource.adaptTo(Task1.class);

//         // Ensure the task1 is properly adapted (add assertion)
//         assertNotNull(task1, "Task1 model adaptation failed, task1 is null");
//     }

//     @Test
//     void testGetTextfield() {
//         // Ensure that the textfield value is correctly set
//         assertNotNull(task1.getTextfield(), "textfield should not be null");
//         assertEquals("Sample Text", task1.getTextfield(), "Textfield value mismatch");
//     }

//     @Test
//     void testGetFileupload() {
//         // Ensure that the fileupload value is correctly set
//         assertNotNull(task1.getFileupload(), "fileupload should not be null");
//         assertEquals("/content/dam/sample.png", task1.getFileupload(), "Fileupload value mismatch");
//     }

//     @Test
//     void testIsCheckbox() {
//         // Ensure that the checkbox value is correctly set
//         assertNotNull(task1.isCheckbox(), "checkbox should not be null");
//         assertEquals("true", task1.isCheckbox(), "Checkbox value mismatch");
//     }

//     @Test
//     void testGetDropdown() {
//         // Ensure that the dropdown value is correctly set
//         assertNotNull(task1.getDropdown(), "dropdown should not be null");
//         assertEquals("Option1", task1.getDropdown(), "Dropdown value mismatch");
//     }

//     @Test
//     void testGetNestedFieldData() {
//         // Retrieve the nested field data from the model
//         List<Task1.Child> nestedFieldData = task1.getNestedFieldData();

//         // Assertions to ensure nested data is not null and has the expected values
//         assertNotNull(nestedFieldData, "NestedFieldData should not be null");
//         assertEquals(2, nestedFieldData.size(), "NestedFieldData size mismatch");
//         assertEquals("Child 1 Text", nestedFieldData.get(0).getRichtext(), "First child's richtext mismatch");
//         assertEquals("Child 2 Text", nestedFieldData.get(1).getRichtext(), "Second child's richtext mismatch");
//     }

//     @Test
//     void testGetNestedFieldDataWithMissingChildren() {
//         // Create a resource without child1 and child2
//         Resource resourceWithoutChildren = context.create().resource("/content/task2", "textfield", "No Children");

//         // Adapt the resource to Task1
//         Task1 taskWithoutChildren = resourceWithoutChildren.adaptTo(Task1.class);

//         // Ensure taskWithoutChildren is adapted correctly
//         assertNotNull(taskWithoutChildren, "Task1 model adaptation failed, taskWithoutChildren is null");

//         // Retrieve the nested field data (should be empty)
//         List<Task1.Child> nestedFieldData = taskWithoutChildren.getNestedFieldData();

//         // Assertions to ensure that the list is empty when no children exist
//         assertNotNull(nestedFieldData, "NestedFieldData should not be null even when children are missing");
//         assertTrue(nestedFieldData.isEmpty(), "NestedFieldData should be empty when no children exist");
//     }

//     @Test
//     void testGetNestedFieldDataWithNullField() {
//         // Create a resource with missing "richtext" field in child1
//         Resource resourceWithNullField = context.create().resource("/content/task3",
//                 "textfield", "Text with missing child field");

//         context.create().resource(resourceWithNullField, "child1", "richtext", null); // Null field

//         Task1 taskWithNullField = resourceWithNullField.adaptTo(Task1.class);

//         // Ensure taskWithNullField is adapted correctly
//         assertNotNull(taskWithNullField, "Task1 model adaptation failed, taskWithNullField is null");

//         List<Task1.Child> nestedFieldData = taskWithNullField.getNestedFieldData();

//         assertNotNull(nestedFieldData, "NestedFieldData should not be null even with missing field");
//         assertTrue(nestedFieldData.size() == 1, "NestedFieldData should have 1 child with a null field");
//         assertNull(nestedFieldData.get(0).getRichtext(), "Child richtext should be null when field is missing");
//     }

//     @Test
//     void testGetNestedFieldDataWithEmptyRichtext() {
//         // Create a resource with an empty "richtext" field in child1
//         Resource resourceWithEmptyField = context.create().resource("/content/task4",
//                 "textfield", "Text with empty child field");

//         context.create().resource(resourceWithEmptyField, "child1", "richtext", ""); // Empty field

//         Task1 taskWithEmptyField = resourceWithEmptyField.adaptTo(Task1.class);

//         // Ensure taskWithEmptyField is adapted correctly
//         assertNotNull(taskWithEmptyField, "Task1 model adaptation failed, taskWithEmptyField is null");

//         List<Task1.Child> nestedFieldData = taskWithEmptyField.getNestedFieldData();

//         assertNotNull(nestedFieldData, "NestedFieldData should not be null even with empty field");
//         assertTrue(nestedFieldData.size() == 1, "NestedFieldData should have 1 child with an empty field");
//         assertEquals("", nestedFieldData.get(0).getRichtext(), "Child richtext should be empty when field is empty");
//     }

//     @Test
//     void testGetNestedFieldDataWithMultipleChildren() {
//         // Create a resource with multiple child resources
//         Resource resourceWithMultipleChildren = context.create().resource("/content/task5",
//                 "textfield", "Multiple children");

//         context.create().resource(resourceWithMultipleChildren, "child1", "richtext", "Child 1 Text");
//         context.create().resource(resourceWithMultipleChildren, "child2", "richtext", "Child 2 Text");
//         context.create().resource(resourceWithMultipleChildren, "child3", "richtext", "Child 3 Text");

//         Task1 taskWithMultipleChildren = resourceWithMultipleChildren.adaptTo(Task1.class);

//         // Ensure taskWithMultipleChildren is adapted correctly
//         assertNotNull(taskWithMultipleChildren, "Task1 model adaptation failed, taskWithMultipleChildren is null");

//         List<Task1.Child> nestedFieldData = taskWithMultipleChildren.getNestedFieldData();

//         assertNotNull(nestedFieldData, "NestedFieldData should not be null with multiple children");
//         assertEquals(3, nestedFieldData.size(), "NestedFieldData size should be 3");
//         assertEquals("Child 1 Text", nestedFieldData.get(0).getRichtext(), "First child's richtext mismatch");
//         assertEquals("Child 2 Text", nestedFieldData.get(1).getRichtext(), "Second child's richtext mismatch");
//         assertEquals("Child 3 Text", nestedFieldData.get(2).getRichtext(), "Third child's richtext mismatch");
//     }
// }
