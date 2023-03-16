package com.musala.task.customexception;

public class ReachedMaxDronesException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ReachedMaxDronesException(String msg) {
    super(msg);
  }
}