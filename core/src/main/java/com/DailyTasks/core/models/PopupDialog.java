package com.DailyTasks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PopupDialog {

    @ValueMapValue
    private String textfield;

    @ValueMapValue
    private String pathfield;

    @ValueMapValue
    private String textfield1;

    @ValueMapValue
    private String richText;  

    @ValueMapValue
    private String dropdown;

    // Getters
    public String getText() {
        return textfield;
    }

    public String getPathfield() {
        return pathfield;
    }

    public String getTextfield() {
        return textfield1;
    }

    public String getRichtext() {  // Changed from getRichText() to getRichtext()
        return richText;
    }

    public String getDropdown() {
        return dropdown;
    }
}
