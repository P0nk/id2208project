package com.mp3application.repositories;

import com.mp3application.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long>{
    
}
