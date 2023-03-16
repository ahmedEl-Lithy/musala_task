package com.musala.task.exceptions;

public class MaxTenDronesException extends RuntimeException {

  //TODO: rename package to customeException
  private static final long serialVersionUID = 1L;

  public MaxTenDronesException(String msg) {
    super(msg);
  }
}