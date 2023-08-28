package com.kode19.financialtracker.financialtracker.paging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class PagingResponse<T> {

  private T data;
  private int currentPage;
  private long totalItems;
  private int totalPages;

}
