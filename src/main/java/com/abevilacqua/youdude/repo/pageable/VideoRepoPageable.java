package com.abevilacqua.youdude.repo.pageable;

import com.abevilacqua.youdude.model.Category;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VideoRepoPageable extends PagingAndSortingRepository<Video, UUID> {

  Page<Video> findAllByUser(final User user, final Pageable pageable);
}
