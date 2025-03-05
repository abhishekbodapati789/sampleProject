package com.DailyTasks.core.models;

import org.apache.sling.api.resource.Resource;
import javax.inject.Inject;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VideoPopup {

    @Inject
    private String videoUrl;

    public String getVideoUrl() {
        return videoUrl;
    }

    public boolean isYouTubeVideo() {
        return videoUrl != null && (videoUrl.contains("youtube.com/watch?v=") || videoUrl.contains("youtu.be/"));
    }

    public String getVideoId() {
        if (isYouTubeVideo()) {
            if (videoUrl.contains("watch?v=")) {
                return videoUrl.substring(videoUrl.indexOf("v=") + 2).split("&")[0];
            } else if (videoUrl.contains("youtu.be/")) {
                return videoUrl.substring(videoUrl.lastIndexOf("/") + 1);
            }
        }
        return "";
    }
}
