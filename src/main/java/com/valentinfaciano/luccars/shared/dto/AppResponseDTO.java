package com.valentinfaciano.luccars.shared.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppResponseDTO<T> {
  private String message;
  private T data;
  private boolean success = true;
  private int statusCode = 200;
  private List<String> errors;
}