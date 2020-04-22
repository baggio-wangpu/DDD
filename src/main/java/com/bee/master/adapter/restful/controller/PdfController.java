package com.bee.master.adapter.restful.controller;

import com.bee.master.common.utils.PdfGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public")
public class PdfController {

    @GetMapping(path = "pdf")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> download() {
        HttpHeaders headers = new HttpHeaders();
        String fileName = "default";
        try {
            fileName = new String(("Certificate").getBytes("gbk"), "iso-8859-1");
            headers.setContentDispositionFormData("attachment", fileName+".pdf");
            headers.setContentType(new MediaType("application","pdf"));
            return new ResponseEntity<byte[]>(PdfGenerator.generate(), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
