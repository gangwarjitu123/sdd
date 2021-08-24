package com.sdd.response;

import lombok.Builder;
import lombok.Data;


@Data
public class ApiResponse<T> {
  T response;
  private int status;
  private String message;
}
