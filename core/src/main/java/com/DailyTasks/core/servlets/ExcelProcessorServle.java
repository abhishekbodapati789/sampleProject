// package com.DailyTasks.core.servlets;

// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.apache.sling.api.resource.*;
// import org.apache.sling.api.servlets.SlingAllMethodsServlet;
// import org.osgi.service.component.annotations.Component;
// import org.osgi.service.component.annotations.Reference;

// import java.io.*;
// import java.util.HashMap;
// import java.util.Map;

// import com.day.cq.dam.api.Asset;
// import com.day.cq.dam.api.AssetManager;

// import javax.servlet.Servlet;
// import javax.servlet.ServletException;

// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;

// /**
//  * AEM Servlet to process and update an Excel file stored in AEM DAM.
//  */
// @Component(service = { Servlet.class })
// @SlingServletPaths("/bin/processExcel")
// public class ExcelProcessorServle extends SlingAllMethodsServlet {

//     private static final String DAM_PATH = "/content/dam/Updated_Book1.xlsx"; // Path in DAM

//     @Reference
//     private ResourceResolverFactory resourceResolverFactory;

//     @Override
//     protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
//         try (ResourceResolver resourceResolver = getResourceResolver()) {
//             if (resourceResolver != null) {
//                 processExcel(resourceResolver, response);
//             } else {
//                 response.getWriter().write("Failed to get ResourceResolver");
//             }
//         } catch (Exception e) {
//             response.getWriter().write("Error: " + e.getMessage());
//         }
//     }

//     private ResourceResolver getResourceResolver() {
//         try {
//             Map<String, Object> authInfo = new HashMap<>();
//             authInfo.put(ResourceResolverFactory.SUBSERVICE, "abhi"); // Use your AEM service user
//             return resourceResolverFactory.getServiceResourceResolver(authInfo);
//         } catch (LoginException e) {
//             e.printStackTrace();
//         }
//         return null;
//     }

//     private void processExcel(ResourceResolver resourceResolver, SlingHttpServletResponse response) throws IOException {
//         Resource fileResource = resourceResolver.getResource(DAM_PATH);
//         if (fileResource == null) {
//             response.getWriter().write("Excel file not found in DAM: " + DAM_PATH);
//             return;
//         }

//         Asset asset = fileResource.adaptTo(Asset.class);
//         if (asset == null) {
//             response.getWriter().write("Failed to adapt resource to Asset.");
//             return;
//         }

//         // Read Excel file from DAM
//         try (InputStream inputStream = asset.getOriginal().getStream();
//              Workbook workbook = new XSSFWorkbook(inputStream)) {

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

//             // Read Sheet2 and replace group IDs
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

//             // Save the updated Excel file back to DAM
//             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//             workbook.write(outputStream);
//             byte[] updatedData = outputStream.toByteArray();

//             AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);
//             if (assetManager != null) {
//                 assetManager.createAsset(DAM_PATH, new ByteArrayInputStream(updatedData), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", true);
//                 response.getWriter().write("Excel file updated successfully in DAM.");
//             } else {
//                 response.getWriter().write("Failed to get AssetManager.");
//             }

//         } catch (IOException e) {
//             response.getWriter().write("Error processing Excel file: " + e.getMessage());
//         }
//     }
// }
