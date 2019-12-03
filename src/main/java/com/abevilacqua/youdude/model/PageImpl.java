package com.abevilacqua.youdude.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PageImpl<T> {

  private long total;

  private List<T> content;

  public PageImpl(List<T> content, Pageable pageable, long total) {
    this.content = content;
    this.total = pageable.toOptional()
        .filter(it -> !content.isEmpty())
        .map(it -> it.getOffset() + content.size())
        .orElse(total);
  }

  public long getTotalPages() { return total; }

  public long getTotalElements() {
    return 0;
  }

  public int getNumber() {
    return 0;
  }

  public int getSize() {
    return 0;
  }

  public int getNumberOfElements() {
    return 0;
  }

  public List<T> getContent() {
    return null;
  }

  public Sort getSort() {
    return null;
  }

  public boolean isFirst() {
    return false;
  }

  public boolean isLast() {
    return false;
  }
}
