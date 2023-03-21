package com.primalimited.filehandler.controller;

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
        try {
            return String.format("File %s is upload successfully", file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while uploading file";
        }
    }
}