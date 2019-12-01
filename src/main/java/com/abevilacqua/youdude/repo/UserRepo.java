package com.abevilacqua.youdude.repo;

import com.abevilacqua.youdude.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Long> {
}
