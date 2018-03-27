package com.mp3application.services;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Kim
 */
public interface StorageService {
    
    void init();
    
    File store(MultipartFile file);
}
