package com.bee.master.adapter.fileserver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties("bees.files")
public class FileConfig {
    private List<String> attachmentTypes = new ArrayList<>();
    private List<String> imageTypes = new ArrayList<>();
    private long attachmentMaxSize;
    private long imageMaxSize;
}
