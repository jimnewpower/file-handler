package com.primalimited.filehandler.controller;

import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @PostMapping("/up")
    public String up(@RequestParam("text") String text) {
        return "Text: " + text;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        Detector detector = new DefaultDetector();
        try {
            TikaInputStream tikaInputStream = TikaInputStream.get(file.getInputStream());
            Metadata metadata = new Metadata();
            String mimeType = detector.detect(tikaInputStream, metadata).toString();
            return String.format("File %s is upload successfully. Detected type: %s", file.getOriginalFilename(), mimeType);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while uploading file";
        }
    }
}