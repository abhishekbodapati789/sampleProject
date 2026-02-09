package com.DailyTasks.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardsGen1v1 {
    @ValueMapValue(name = "labelName")
    private String labelName;

    @ValueMapValue(name = "title")
    private String title;

    @ChildResource(name = "cards")
    private List<CardItem> cards;

    public String getLabelName() {
        return labelName;
    }

    public String getTitle() {
        return title;
    }

    public List<CardItem> getCards() {
        // Defensive: Only return up to 5 cards
        if (cards != null && cards.size() > 5) {
            return cards.subList(0, 5);
        }
        return cards;
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class CardItem {
        @ValueMapValue(name = "description")
        private String description;

        @ValueMapValue(name = "url")
        private String url;

        @ValueMapValue(name = "image")
        private String image;

        public String getDescription() {
            return description;
        }

        public String getUrl() {
            return url;
        }

        public String getImage() {
            return image;
        }
    }
}
