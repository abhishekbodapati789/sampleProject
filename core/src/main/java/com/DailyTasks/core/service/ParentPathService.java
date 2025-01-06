package com.DailyTasks.core.service;

import com.DailyTasks.core.Config.ParentPathConfig;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ParentPathService.class, immediate = true)
@Designate(ocd=ParentPathConfig.class)
public class ParentPathService {

    private static final Logger log = LoggerFactory.getLogger(ParentPathService.class);

    private String path;

    @Activate
    public void start(ParentPathConfig config){
       this.path= config.getPath();

    }

    public String getPath(){
        return path;
    }

}
