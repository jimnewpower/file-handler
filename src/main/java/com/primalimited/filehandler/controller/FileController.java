package com.primalimited.filehandler.controller;

import com.google.gson.Gson;
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
        String message = "Error while uploading file";
        String mimeType = "unknown";

        Detector detector = new DefaultDetector();
        try {
            TikaInputStream tikaInputStream = TikaInputStream.get(file.getInputStream());
            Metadata metadata = new Metadata();
            mimeType = detector.detect(tikaInputStream, metadata).toString();
            message = "File uploaded successfully";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Gson().toJson(new Response(message, mimeType, file.getOriginalFilename()));
    }

    private static class Response {
        private String message;
        private String mimeType;
        private String filename;

        public Response(String message, String mimeType, String filename) {
            this.message = message;
            this.mimeType = mimeType;
            this.filename = filename;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }
}