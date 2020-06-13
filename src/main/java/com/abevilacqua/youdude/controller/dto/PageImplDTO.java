package com.abevilacqua.youdude.controller.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Builder
public class PageImplDTO<T> {

  private final List<T> content;

  private final long totalElements;

  private final int totalPages, size;

  private final boolean first, last;

  private final Sort sort;

  public static PageImplDTO pageMapper(final Page page) {
    return PageImplDTO
        .builder()
        .content(page.getContent())
        .totalElements(page.getTotalElements())
        .first(page.isFirst())
        .last(page.isLast())
        .totalPages(page.getTotalPages())
        .size(page.getSize())
        .sort(page.getSort())
        .build();
  }

}
