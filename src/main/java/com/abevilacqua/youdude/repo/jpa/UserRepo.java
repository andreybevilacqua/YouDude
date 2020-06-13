package com.abevilacqua.youdude.repo.jpa;

import com.abevilacqua.youdude.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
}
