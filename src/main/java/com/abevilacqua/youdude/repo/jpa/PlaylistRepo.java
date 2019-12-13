package com.abevilacqua.youdude.repo.jpa;

import com.abevilacqua.youdude.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepo extends JpaRepository<Playlist, Long> {
}
