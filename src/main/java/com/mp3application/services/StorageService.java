package com.mp3application.services;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    
    void init();
    
    File store(MultipartFile file);
}
