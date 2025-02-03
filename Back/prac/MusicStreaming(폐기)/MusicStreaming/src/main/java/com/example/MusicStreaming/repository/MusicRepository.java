package com.example.MusicStreaming.repository;

import com.example.MusicStreaming.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    @Override
    List<Music> findAll();

    List<Music> findByTitle(String title);
}
