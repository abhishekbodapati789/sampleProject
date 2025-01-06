package com.DailyTasks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Task6 {

    @ValueMapValue String textfield;

    @ValueMapValue String pathfield;

    @ValueMapValue String datepicker;

    
    public String getTextField() {
        return textfield;
    }

    public String getPathField() {
        return pathfield;
    }

    public String getSelectedDate() {
        return datepicker;
    }

    
    public boolean isExpired() {
        if (datepicker == null || datepicker.isEmpty()) {
            return false; 
        }

        try {
            
            OffsetDateTime selectedDateTime = OffsetDateTime.parse(datepicker, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            LocalDate selectedDate = selectedDateTime.toLocalDate();
            LocalDate today = LocalDate.now();
            return selectedDate.isBefore(today);
        } catch (Exception e) {
            return true;
        }
    }
}
