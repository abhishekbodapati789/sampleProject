package com.DailyTasks.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Task1 {

    @ValueMapValue
    private String textfield;

    @ValueMapValue
    private String fileupload;

    @ValueMapValue
    private String checkbox; 

    @ValueMapValue
    private String dropdown;

    @ChildResource
    private List<Child> nestedFieldData;

    public String getTextfield() {
        return textfield;
    }

    public String getFileupload() {
        return fileupload;
    }

    public String isCheckbox() { 
        return checkbox;
    }

    public String getDropdown() {
        return dropdown;
    }

    public List<Child> getNestedFieldData() {
        return nestedFieldData;
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class Child {
        @ValueMapValue
        private String richtext;

        public String getRichtext() {
            return richtext;
        }
    }
}
