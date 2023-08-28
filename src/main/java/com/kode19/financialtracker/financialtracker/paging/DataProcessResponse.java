package com.kode19.financialtracker.financialtracker.paging;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DataProcessResponse<T> {

  private T data;
  private LocalDateTime timestamp;
  private String path;
  private Integer statusCode;
  private String message;

}
