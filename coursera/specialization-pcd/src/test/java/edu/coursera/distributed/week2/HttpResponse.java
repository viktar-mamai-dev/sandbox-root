package edu.coursera.distributed.week2;

public class HttpResponse {
  public final int code;
  public final String body;

  public HttpResponse(final int setCode, final String setBody) {
    code = setCode;
    body = setBody;
  }
}
