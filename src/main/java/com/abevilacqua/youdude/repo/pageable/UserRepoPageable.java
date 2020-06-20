package com.abevilacqua.youdude.repo.pageable;

import com.abevilacqua.youdude.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepoPageable extends PagingAndSortingRepository<User, UUID> {

  @Query("SELECT u FROM user_youdude u ORDER BY u.name")
  Page<User> findAll(Pageable pageable);
}
