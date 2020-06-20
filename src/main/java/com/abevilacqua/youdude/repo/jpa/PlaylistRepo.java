package com.abevilacqua.youdude.repo.jpa;

import com.abevilacqua.youdude.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PlaylistRepo extends JpaRepository<Playlist, UUID> {

  @Query("SELECT p FROM Playlist p ORDER BY p.name")
  List<Playlist> findAll();

  @Query("SELECT p FROM Playlist p WHERE p.user = ?1")
  List<Playlist> findAllByUser(final UUID user);

  @Query("DELETE FROM Playlist p WHERE p.user = ?1")
  void deleteAllByUserId(final UUID user);
}
