package org.sopt.sopkathon_server.global.response.error;

public interface ErrorType {
    int getStatus();
    String getCode();
    String getMessage();
}