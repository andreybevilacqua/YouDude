package com.abevilacqua.youdude.repo.pageable;

import com.abevilacqua.youdude.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoRepoPageable extends PagingAndSortingRepository<Video, UUID> {

  @Query("SELECT v FROM Video v WHERE v.user = ?1")
  Page<Video> findAllByUser(UUID userId, Pageable pageable);
}
