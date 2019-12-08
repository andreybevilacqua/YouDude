package com.abevilacqua.youdude.repo;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepo extends PagingAndSortingRepository<Playlist, Long> {

  Page<Playlist> findAllByUser(User user, Pageable pageable);
}
