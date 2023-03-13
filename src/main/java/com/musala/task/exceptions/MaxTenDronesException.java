package com.musala.task.exceptions;

public class MaxTenDronesException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public MaxTenDronesException(String msg) {
    super(msg);
  }
}