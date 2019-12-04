package com.abevilacqua.youdude.controller.dto;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageImplDTO<T> extends PageImpl<T> {

  private List<T> content;

  private long totalElements;

  private boolean first, last;

  private int totalPages, size, numberOfElements;

  public PageImplDTO(List<T> content, Pageable pageable, long total) {
    super(content, pageable, total);

    this.content = content;
    this.totalPages = super.getTotalPages();
    this.totalElements = super.getTotalElements();
    this.first = super.isFirst();
    this.last = super.isLast();
    this.size = super.getSize();
    this.numberOfElements = super.getNumberOfElements();
  }

  @Override
  public List<T> getContent() {
    return content;
  }

  @Override
  public long getTotalElements() {
    return totalElements;
  }

  @Override
  public boolean isFirst() {
    return first;
  }

  @Override
  public boolean isLast() {
    return last;
  }

  @Override
  public int getTotalPages() {
    return totalPages;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public int getNumberOfElements() {
    return numberOfElements;
  }
}
