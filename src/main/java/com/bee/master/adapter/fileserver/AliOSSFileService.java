package com.bee.master.adapter.fileserver;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@Primary
@ConditionalOnProperty(prefix = "file", name = "handler", havingValue = "oss")
public class AliOSSFileService extends FileService {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public String singleFileUpload(MultipartFile file, String subFolder) throws IOException {
        String freshName = freshName(file);

        byte[] bytes = file.getBytes();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(bytes.length);
        objectMetadata.setContentType(getMimeType(bytes, freshName));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        amazonS3.putObject(subFolder, freshName, byteArrayInputStream, objectMetadata);

        return prepareFilePath(freshName, subFolder);
    }

    @Override
    public byte[] getFile(String subFolder, String fileName) throws IOException {
        S3Object object = amazonS3.getObject(subFolder, fileName);

        return IOUtils.toByteArray(object.getObjectContent());
    }

    @Override
    public void deleteFileByUri(String fileUri) {
        String[] split = fileUri.split(File.separator);
        if (split.length != 2) {
            return;
        }
        if (amazonS3.doesObjectExist(split[0], split[1])) {
            amazonS3.deleteObject(split[0], split[1]);
        }
    }
}
