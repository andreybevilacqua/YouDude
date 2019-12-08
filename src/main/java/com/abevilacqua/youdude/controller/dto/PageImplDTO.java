package com.abevilacqua.youdude.controller.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
public class PageImplDTO<T> extends PageImpl<T> {

  private List<T> content;

  private long totalElements;

  private boolean first, last;

  private int totalPages, size, numberOfElements;

  public PageImplDTO(List<T> content, Pageable pageable, long total) {
    super(content, pageable, total);
  }

  public PageImplDTO() {
    super(null, null, 0);
  }

  public static PageImplDTO pageMapper(Page pageImpl) {
    return new PageImplDTO.Builder()
        .content(pageImpl.getContent())
        .totalElements(pageImpl.getTotalElements())
        .first(pageImpl.isFirst())
        .last(pageImpl.isLast())
        .totalPages(pageImpl.getTotalPages())
        .size(pageImpl.getSize())
        .numberOfElements(pageImpl.getNumberOfElements())
        .build();
  }

  public static class Builder {

    private List content;

    private long totalElements;

    private boolean first, last;

    private int totalPages, size, numberOfElements;

    public Builder content(List content) {
      this.content = content;
      return this;
    }

    public Builder totalElements(long totalElements) {
      this.totalElements = totalElements;
      return this;
    }

    public Builder first(boolean first) {
      this.first = first;
      return this;
    }

    public Builder last(boolean last) {
      this.last = last;
      return this;
    }

    public Builder totalPages(int totalPages) {
      this.totalPages = totalPages;
      return this;
    }

    public Builder size(int size) {
      this.size = size;
      return this;
    }

    public Builder numberOfElements(int numberOfElements) {
      this.numberOfElements = numberOfElements;
      return this;
    }

    public PageImplDTO build() {
      PageImplDTO pageImplDTO = new PageImplDTO();
      pageImplDTO.content = this.content;
      pageImplDTO.totalElements = this.totalElements;
      pageImplDTO.first = this.first;
      pageImplDTO.last = this.last;
      pageImplDTO.totalPages = this.totalPages;
      pageImplDTO.size = this.size;
      pageImplDTO.numberOfElements = numberOfElements;

      return pageImplDTO;
    }

  }
}
