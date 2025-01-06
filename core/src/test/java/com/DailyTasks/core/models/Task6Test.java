// package com.DailyTasks.core.models;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;

// import java.time.OffsetDateTime;

// public class Task6Test {

//     private Task6 task6;

//     @BeforeEach
//     void setUp() {
//         // Create a Task6 instance with valid data
//         task6 = new Task6();
//     }

//     @Test
//     void testGetTextField() {
//         task6.textfield = "Sample Text";
//         assertEquals("Sample Text", task6.getTextField(), "Textfield value mismatch");
//     }

//     @Test
//     void testGetPathField() {
//         task6.pathfield = "/content/dam/file.png";
//         assertEquals("/content/dam/file.png", task6.getPathField(), "Pathfield value mismatch");
//     }

//     @Test
//     void testGetSelectedDate() {
//         task6.datepicker = "2024-12-31T00:00:00+00:00";
//         assertEquals("2024-12-31T00:00:00+00:00", task6.getSelectedDate(), "Selected date mismatch");
//     }

//     @Test
//     void testIsExpiredDateBeforeToday() {
//         // Set a date before today
//         task6.datepicker = "2023-12-30T00:00:00+00:00";
//         assertTrue(task6.isExpired(), "Date should be expired");
//     }

//     @Test
//     void testIsExpiredDateAfterToday() {
//         // Set a date after today
//         task6.datepicker = "2025-12-31T00:00:00+00:00";
//         assertFalse(task6.isExpired(), "Date should not be expired");
//     }

//     @Test
// void testIsExpiredDateEqualToday() {
//     // Set a date equal to today's date
//     task6.datepicker = OffsetDateTime.now().toLocalDate().atStartOfDay(OffsetDateTime.now().getOffset()).toString() + "T00:00:00+00:00";
    
//     assertFalse(task6.isExpired(), "Date should not be expired");
// }


//     @Test
//     void testIsExpiredNullDate() {
//         // Set datepicker to null
//         task6.datepicker = null;
//         assertFalse(task6.isExpired(), "Date should not be expired if date is null");
//     }

//     @Test
//     void testIsExpiredEmptyDate() {
//         // Set datepicker to empty
//         task6.datepicker = "";
//         assertFalse(task6.isExpired(), "Date should not be expired if date is empty");
//     }

//     @Test
//     void testIsExpiredInvalidDateFormat() {
//         // Set an invalid date format
//         task6.datepicker = "invalid-date";
//         assertTrue(task6.isExpired(), "Date should be considered expired if format is invalid");
//     }
// }
