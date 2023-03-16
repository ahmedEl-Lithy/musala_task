package com.musala.task.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorMessage {
  //TODO: move to DTO
  private int statusCode;
  private Date timestamp;
  private List<String> message;
  private String description;
}