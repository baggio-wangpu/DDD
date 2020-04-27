package com.bee.master.adapter.fileserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/{subFolder}/{name}", produces = IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable("subFolder") String subFolder,
                         @PathVariable("name") String name,
                         HttpServletResponse response) throws IOException {

        byte[] bytes = fileService.getFile(subFolder, name);
        response.setHeader(CONTENT_TYPE, fileService.getMimeType(bytes, name));
        response.getOutputStream().write(bytes);
        response.flushBuffer();
    }
}
