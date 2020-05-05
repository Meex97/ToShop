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

    @PostMapping("/upload5")
    public String handleFileUpload(@RequestParam("file") MultipartFile file){
        String a = "sadad";
        return a ;
    }
}
