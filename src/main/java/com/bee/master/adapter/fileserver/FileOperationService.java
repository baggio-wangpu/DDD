package com.bee.master.adapter.fileserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@ConditionalOnProperty(prefix = "file", name = "handler", havingValue = "file")
public class FileOperationService extends FileService {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public String singleFileUpload(MultipartFile file, String subFolder) throws IOException {
        String freshName = freshName(file);
        String newFile = subFolder + File.separator + freshName;

        Path uploadFolder = Paths.get(uploadPath + subFolder);
        if (!uploadFolder.toFile().exists()) {
            Files.createDirectory(uploadFolder);
        }
        Path uploadFilePath = Paths.get(uploadPath + newFile);
        if (!uploadFilePath.toFile().exists()) {
            Files.write(uploadFilePath, file.getBytes());
        }

        return prepareFilePath(freshName, subFolder);
    }

    @Override
    public byte[] getFile(String subFolder, String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(uploadPath + subFolder + File.separator + fileName));
    }

    @Override
    public void deleteFileByUri(String fileUri) {
        Path uploadFilePath = Paths.get(uploadPath + fileUri);
        if (!uploadFilePath.toFile().exists()) {
            return;
        }

        try {
            Files.delete(uploadFilePath);
        } catch (IOException ioe) {
            log.warn("Cannot delete file " + fileUri, ioe);
        }

    }
}
