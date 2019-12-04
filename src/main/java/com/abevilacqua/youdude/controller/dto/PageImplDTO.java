package com.abevilacqua.youdude.controller.dto;

import com.abevilacqua.youdude.model.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Builder
@Getter
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

  public static PageImplDTO mapper(Page pageImpl) {
    return PageImplDTO.builder()
        .content(pageImpl.getContent())
        .totalElements(pageImpl.getTotalElements())
        .first(pageImpl.isFirst())
        .last(pageImpl.isLast())
        .totalPages(pageImpl.getTotalPages())
        .size(pageImpl.getSize())
        .numberOfElements(pageImpl.getNumberOfElements())
        .build();
  }
}
