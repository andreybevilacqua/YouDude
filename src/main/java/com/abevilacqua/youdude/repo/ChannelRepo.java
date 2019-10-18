package com.abevilacqua.youdude.repo;

import com.abevilacqua.youdude.model.Channel;
import com.abevilacqua.youdude.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {

  List<Channel> findAllByOwner(User owner);
}
