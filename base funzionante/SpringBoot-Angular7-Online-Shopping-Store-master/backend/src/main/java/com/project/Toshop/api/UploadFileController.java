package com.project.Toshop.api;

import com.project.Toshop.entity.Client;
import com.project.Toshop.entity.FileModel;
import com.project.Toshop.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.LinkedHashMap;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UploadFileController {

    @Autowired
    FileRepository fileRepository;

    /*
     * MultipartFile Upload
     */
    @PostMapping("/api/file/upload2")
    public String uploadMultipartFile(@RequestParam("file") LinkedHashMap file, RedirectAttributes redirectAttributes)  {
        System.out.println("ciao");
        System.out.println(file);
        return "ciao";
        /*try {
            // save file to PostgreSQL
            FileModel filemode = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            fileRepository.save(filemode);
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (  Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }*/
    }

    @PostMapping("/api/file/upload")
    public ResponseEntity<?> save(@RequestBody Object file) {

        System.out.println("ciao");
        System.out.println(file);
        System.out.println(file.getClass());

        return ResponseEntity.badRequest().build();
    }
}
