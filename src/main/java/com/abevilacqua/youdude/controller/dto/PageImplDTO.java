package com.abevilacqua.youdude.controller.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class PageImplDTO<T> {

  private List<T> content;

  private long totalElements;

  private int totalPages, size;

  private boolean first, last;

  public static PageImplDTO pageMapper(Page page) {
    return PageImplDTO.builder()
        .content(page.getContent())
        .totalElements(page.getTotalElements())
        .first(page.isFirst())
        .last(page.isLast())
        .totalPages(page.getTotalPages())
        .size(page.getSize())
        .build();
  }

}
