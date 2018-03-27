package com.mp3application.controllers;

import com.mp3application.models.Track;
import com.mp3application.repositories.TrackRepository;
import com.mp3application.services.FileSystemStorageService;
import com.mp3application.services.FileToData;
import com.mpatric.mp3agic.Mp3File;
import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Kim
 */
@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class TrackController {

    @Autowired
    TrackRepository trackRepository;

    @GetMapping(value = "/tracks")
    public List<Track> getTracks() {
        return trackRepository.findAll();
    }

    @GetMapping(value = "/tracks/{id}")
    public Track getTrack(@PathVariable(value = "id") Long trackId) {
        Track track = trackRepository.findOne(trackId);
        if (track == null) {
            //implement exception
            return null;
        }
        return track;
    }

    @PostMapping(value = "/tracks")
    public void addTrack(@RequestParam("file") MultipartFile track) {
        FileSystemStorageService fileSystemStorageService = new FileSystemStorageService();
        File trackFile = fileSystemStorageService.store(track);
        trackRepository.save(FileToData.getTrack(trackFile));
        FileSystemUtils.deleteRecursively(trackFile);
        System.out.println(track.getOriginalFilename() + " added.");
    }

    @PutMapping(value = "/tracks/{id}")
    public void updateTrack(@PathVariable(value = "id") Long trackId, @RequestBody Track track) {
        Track t = trackRepository.findOne(trackId);
        t.setTitle(track.getTitle());
        t.setArtist(track.getArtist());
        t.setAlbum(track.getAlbum());
        t.setLength(track.getLength());
        t.setYear(track.getYear());
        trackRepository.save(t);
    }

    @DeleteMapping(value = "tracks/{id}")
    public void deleteTrack(@PathVariable(value = "id") Long trackId) {
        Track track = trackRepository.findOne(trackId);
        trackRepository.delete(track);
    }

}
