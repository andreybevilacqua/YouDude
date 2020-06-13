package com.abevilacqua.youdude.service;

import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class GenericService {

  public static <R extends PagingAndSortingRepository<T, UUID>, T> CompletableFuture<Page<T>> getAll(R repository,
                                                                                                     final int page,
                                                                                                     final int size,
                                                                                                     final String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return supplyAsync(() -> {
      System.out.println("Thread running inside of GenericService supplyAsync: " + Thread.currentThread());
      return repository.findAll(pageable);
    });
  }

  @SneakyThrows
  public static void simulateSlowService() {
    Thread.sleep(3000L);
  }
}
