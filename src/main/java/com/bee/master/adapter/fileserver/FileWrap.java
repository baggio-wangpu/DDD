package com.bee.master.adapter.fileserver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileWrap {
    private String originalName;

    private long size;

    private String fileType;

    private String fileUrl;

    public static FileWrap of(MultipartFile file, String fileUrl, String fileType) {
        return FileWrap.builder()
                .originalName(file.getOriginalFilename())
                .size(file.getSize())
                .fileType(fileType)
                .fileUrl(fileUrl)
                .build();
    }
}
