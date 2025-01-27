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
    private String pathfield;

    @ValueMapValue
    private String textfield;

    @ValueMapValue
    private boolean checkbox;

    @ChildResource
    private List<MultiFieldItem> multifield;

    public String getPathfield() {
        return pathfield;
    }

    public String getTextfield() {
        return textfield;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public List<MultiFieldItem> getMultifield() {
        return multifield;
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class MultiFieldItem {
        @ValueMapValue
        private String text;

        @ValueMapValue
        private String date;

        public String getText() {
            return text;
        }

        public String getDate() {
            return date;
        }
    }
}
