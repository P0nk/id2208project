package com.mp3application.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

   private final String location = "C:\\Users\\Per\\Documents\\NetBeansProjects\\Mp3Application\\tracks\\";
   private final Path rootLocation;

   @Autowired
   public FileSystemStorageService() {
       this.rootLocation = Paths.get(location);
   }

   @Override
   public File store(MultipartFile file) {
       String filename = StringUtils.cleanPath(file.getOriginalFilename());
       try {
           if (file.isEmpty()) {
               throw new StorageException("Failed to store empty file " + filename);
           }
           if (filename.contains("..")) {
               // This is a security check
               throw new StorageException(
                       "Cannot store file with relative path outside current directory "
                               + filename);
           }
           Path fileLocation = rootLocation.resolve(filename);
           Files.copy(file.getInputStream(), fileLocation,
                   StandardCopyOption.REPLACE_EXISTING);
           return new File(fileLocation.toString());
       }
       catch (IOException e) {
           throw new StorageException("Failed to store file " + filename, e);
       }
   }

   @Override
   public void init() {
       try {
           Files.createDirectories(rootLocation);
       }
       catch (IOException e) {
           throw new StorageException("Could not initialize storage", e);
       }
   }
}
