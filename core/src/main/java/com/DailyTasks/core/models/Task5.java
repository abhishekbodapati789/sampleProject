package com.DailyTasks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Task5 {

    @ValueMapValue
    private String checkbox;

    @ValueMapValue
    @Default(values = "")
    private String textfield;

    public String isCheckbox() {
        return checkbox;
    }

    public String getTextfield() {
        return textfield;
    }
}
