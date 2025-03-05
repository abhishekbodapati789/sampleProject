// package com.DailyTasks.core.models;

// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.models.annotations.DefaultInjectionStrategy;
// import org.apache.sling.models.annotations.Model;
// import org.apache.sling.models.annotations.injectorspecific.SlingObject;

// import javax.inject.Inject;

// @Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
// public class ParentContentFragmentModel {

//     @SlingObject
//     private Resource resource;

//     @SlingObject
//     private ResourceResolver resourceResolver;

//     @Inject
//     private String text;

//     @Inject
//     private String contentFragmentPath;

//     /**
//      * Fetch the inherited text property from the parent page.
//      */
//     public String getText() {
//         return getInheritedProperty("text");
//     }

//     /**
//      * Fetch the inherited content fragment path from the parent page.
//      */
//     public String getContentFragmentPath() {
//         return getInheritedProperty("contentFragmentPath");
//     }

//     /**
//      * Fetch the content fragment resource from the given path.
//      */
//     public Resource getContentFragment() {
//         String fragmentPath = getContentFragmentPath();
//         return fragmentPath != null ? resourceResolver.getResource(fragmentPath) : null;
//     }

//     /**
//      * Utility method to get inherited properties from parent pages.
//      */
//     private String getInheritedProperty(String propertyName) {
//         Resource parentResource = resource.getParent();
//         while (parentResource != null) {
//             String value = parentResource.getValueMap().get(propertyName, String.class);
//             if (value != null) {
//                 return value;
//             }
//             parentResource = parentResource.getParent();
//         }
//         return null;
//     }
// }
