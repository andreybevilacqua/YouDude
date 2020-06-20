package com.abevilacqua.youdude.repo.pageable;

import com.abevilacqua.youdude.model.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaylistRepoPageable extends PagingAndSortingRepository<Playlist, UUID> {

  @Query("SELECT p FROM Playlist p ORDER BY p.name")
  Page<Playlist> findAll(Pageable pageable);

  @Query("SELECT p FROM Playlist p WHERE p.user = ?1")
  Page<Playlist> findAllByUser(UUID user, Pageable pageable);
}
