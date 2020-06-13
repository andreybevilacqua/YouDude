package com.abevilacqua.youdude.repo.pageable;

import com.abevilacqua.youdude.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepoPageable extends PagingAndSortingRepository<User, UUID> {
}
