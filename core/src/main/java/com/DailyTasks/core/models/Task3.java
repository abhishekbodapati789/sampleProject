// package com.DailyTasks.core.models;

// import java.util.List;

// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.models.annotations.DefaultInjectionStrategy;
// import org.apache.sling.models.annotations.Model;
// import org.apache.sling.models.annotations.injectorspecific.ChildResource;
// import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

// @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
// public class Task3 {

//     @ValueMapValue
//     private String path;

//     @ValueMapValue
//     private String link;

//     @ValueMapValue
//     private Boolean checkbox;

//     @ChildResource
//     private List<Multi> multifield;

//     @ChildResource
//     private List<Multifield> multi;

//     @ValueMapValue
//     private String country;

//     public String getPath() {
//         return path;
//     }

//     public String getLink() {
//         return link;
//     }

//     public Boolean getCheckbox() {
//         return checkbox;
//     }

//     public List<Multi> getMultifield() {
//         return multifield;
//     }

//     public List<Multifield> getMulti() {
//         return multi;
//     }

//     public String getCountry() {
//         return country;
//     }

//     @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
//     public static class Multi {

//         @ValueMapValue(name = "fname")
//         private String fname;

//         @ValueMapValue
//         private String image;

//         public String getFname() {
//             return fname;
//         }

//         public String getImage() {
//             return image;
//         }
//     }

//     @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
//     public static class Multifield {

//         @ValueMapValue
//         private String desktopIcon;

//         @ValueMapValue
//         private String mobileIcon;

//         @ChildResource
//         private List<MultiNested> nestedMulti;

//         public String getDesktopIcon() {
//             return desktopIcon;
//         }

//         public String getMobileIcon() {
//             return mobileIcon;
//         }

//         public List<MultiNested> getNestedMulti() {
//             return nestedMulti;
//         }

//         @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
//         public static class MultiNested {

//             @ValueMapValue
//             private String NavigationURL;


//             public String getNavigationURL() {
//                 return NavigationURL;
//             }
//         }
//     }
// }
