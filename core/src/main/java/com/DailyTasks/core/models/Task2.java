package com.DailyTasks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Task2 {

    @ValueMapValue
    private String textfield;

    @ChildResource
    private List<Child> multiFieldData;

    public String getTextfield() {
        return textfield;
    }

    public List<Child> getMultiFieldData() {
        return multiFieldData;
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class Child {

        @ValueMapValue
        private String plaintext;

        @ValueMapValue
        private String fileupload;

        @ValueMapValue
        private String checkbox;

        @ValueMapValue
        private String dropdown;

        @ChildResource
        private List<NestedChild> nestedFieldData;

        public String getPlaintext() {
            return plaintext;
        }

        public String getFileupload() {
            return fileupload;
        }

        public String getCheckbox() {
            return checkbox;
        }

        public String getDropdown() {
            return dropdown;
        }

        public List<NestedChild> getNestedFieldData() {
            return nestedFieldData;
        }

        @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
        public static class NestedChild {

            @ValueMapValue
            private String richtext;

            public String getRichtext() {
                return richtext;
            }
        }
    }
}
