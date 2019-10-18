package com.abevilacqua.youdude.repo;

import com.abevilacqua.youdude.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepo extends JpaRepository<Channel, Long> {
}
