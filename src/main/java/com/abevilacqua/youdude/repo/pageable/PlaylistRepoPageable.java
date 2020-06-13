package com.abevilacqua.youdude.repo.pageable;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaylistRepoPageable extends PagingAndSortingRepository<Playlist, UUID> {

  Page<Playlist> findAllByUser(final User user, final Pageable pageable);
}
