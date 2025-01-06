package com.DailyTasks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Mockint {

    @ValueMapValue
    public String text;

    @ValueMapValue
    public String checkbox;

    public String getCheckbox() {
        return checkbox;
    }

    public String getText() {
        return text;
    }
    
}
