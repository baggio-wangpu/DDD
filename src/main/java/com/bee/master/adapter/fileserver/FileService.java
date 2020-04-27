package com.bee.master.adapter.fileserver;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.bee.master.common.exception.BaseException.badRequest;
import static java.util.Objects.requireNonNull;

@Slf4j
public abstract class FileService {
    @Autowired
    private FileConfig fileConfig;

    private final Tika tika = new Tika();

    @Value("${file.static.access.path}")
    private String staticAccessPath;

    public abstract String singleFileUpload(MultipartFile file, String subFolder) throws IOException;

    public abstract byte[] getFile(String subFolder, String fileName) throws IOException;

    public abstract void deleteFileByUri(String fileUri);

    public String getMimeType(byte[] bytes, String name) {
        return tika.detect(bytes, name);
    }

    @SneakyThrows
    private String getMimeType(InputStream stream, String name) {
        return tika.detect(stream, name);
    }

    String freshName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            throw badRequest("file_name_invalid");
        }
        String suffixName = fileName.substring(requireNonNull(fileName).lastIndexOf(".") + 1);

        return UUID.randomUUID().toString() + "." + suffixName;
    }

    String prepareFilePath(String fileName, String subFolder) {
        return staticAccessPath + subFolder + File.separator + fileName;
    }

    FileWrap uploadAttachment(MultipartFile file) throws IOException {
        String fileType = getMimeType(file.getInputStream(), file.getOriginalFilename());

        if (file.getSize() > fileConfig.getAttachmentMaxSize()) {
            throw badRequest("attachment_over_25M");
        }

        if (!fileConfig.getAttachmentTypes().contains(fileType)) {
            log.warn("Invalid_file_type! Received file type is " + fileType);
            throw badRequest("invalid_file_type");
        }

        String fileUrl = singleFileUpload(file, "attachment");

        return FileWrap.of(file, fileUrl, fileType);
    }

    String uploadImg(MultipartFile file) throws IOException {
        String fileType = getMimeType(file.getInputStream(), file.getOriginalFilename());

        if (file.getSize() > fileConfig.getImageMaxSize()) {
            throw badRequest("img_over_2M");
        }

        if (!fileConfig.getImageTypes().contains(fileType)) {
            throw badRequest("invalid_file_type");
        }

        return singleFileUpload(file, "img");
    }
}
