// package com.DailyTasks.core.servlets;

// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.apache.sling.api.servlets.SlingAllMethodsServlet;
// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;
// import org.apache.sling.api.servlets.ServletResolverConstants;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.osgi.service.component.annotations.Component;

// import javax.servlet.Servlet;
// import javax.servlet.ServletException;
// import java.io.*;
// import java.util.HashMap;
// import java.util.Map;

// /**
//  * Path-Based Servlet to Process Excel Sheets in AEM (Dynamic File Path)
//  */
// @Component(
//         service = { Servlet.class },
//         property = {
//                 ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/processExcel",
//                 ServletResolverConstants.SLING_SERVLET_METHODS + "=POST"
//         }
// )
// public class ExcelProcessor11 extends SlingAllMethodsServlet {

//     @Override
//     protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
//         response.setContentType("text/plain");
//         response.setCharacterEncoding("UTF-8");
//         PrintWriter out = response.getWriter();

//         // 1. Try fetching file path from headers
//         String filePath = request.getHeader("file-path");

//         // 2. If not found, try from request parameters (Postman Body)
//         if (filePath == null || filePath.isEmpty()) {
//             filePath = request.getParameter("file-path");
//         }

//         // 3. Validate file path
//         if (filePath == null || filePath.isEmpty()) {
//             out.write("Error: Missing 'file-path' in headers or request parameters.");
//             return;
//         }

//         // Use Sling's ResourceResolver to resolve the file location in the AEM repository
//         ResourceResolver resolver = request.getResourceResolver();
//         String resolvedPath = resolver.map(filePath); // This will map the relative path to the correct repository path

//         // If resolved path is null or empty, report an error
//         if (resolvedPath == null || resolvedPath.isEmpty()) {
//             out.write("Error: Invalid file path or unable to resolve the path: " + filePath);
//             return;
//         }

//         // Ensure the resolved path points to a file
//         File file = new File(resolvedPath);
//         if (!file.exists()) {
//             out.write("Error: Excel file not found at: " + resolvedPath);
//             return;
//         }

//         try {
//             processExcel(resolvedPath, out);
//         } catch (Exception e) {
//             out.write("Error processing Excel file: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }

//     private void processExcel(String filePath, PrintWriter out) throws IOException {
//         File file = new File(filePath);

//         if (!file.exists()) {
//             out.write("Error: Excel file not found at: " + filePath);
//             return;
//         }

//         try (FileInputStream fis = new FileInputStream(file);
//              Workbook workbook = new XSSFWorkbook(fis)) {

//             // Read Sheet1 and create mapping
//             Sheet sheet1 = workbook.getSheetAt(0);
//             Map<String, String> groupMapping = new HashMap<>();

//             for (Row row : sheet1) {
//                 Cell groupIdCell = row.getCell(0);
//                 Cell groupNameCell = row.getCell(1);
//                 if (groupIdCell != null && groupNameCell != null) {
//                     groupMapping.put(groupIdCell.getStringCellValue(), groupNameCell.getStringCellValue());
//                 }
//             }

//             // Read Sheet2 and replace group IDs with group names
//             Sheet sheet2 = workbook.getSheetAt(1);
//             for (Row row : sheet2) {
//                 Cell groupIdCell = row.getCell(0);
//                 if (groupIdCell != null) {
//                     String[] groupIds = groupIdCell.getStringCellValue().split(",");
//                     StringBuilder updatedNames = new StringBuilder();

//                     for (String groupId : groupIds) {
//                         updatedNames.append(groupMapping.getOrDefault(groupId.trim(), groupId)).append(",");
//                     }

//                     // Remove last comma
//                     if (updatedNames.length() > 0) {
//                         updatedNames.setLength(updatedNames.length() - 1);
//                     }

//                     groupIdCell.setCellValue(updatedNames.toString());
//                 }
//             }

//             // Save the updated file
//             try (FileOutputStream fos = new FileOutputStream(file)) {
//                 workbook.write(fos);
//                 out.write("Excel file updated successfully at: " + filePath);
//             }

//         } catch (IOException e) {
//             out.write("Error processing Excel file: " + e.getMessage());
//             throw e;
//         }
//     }
// }
