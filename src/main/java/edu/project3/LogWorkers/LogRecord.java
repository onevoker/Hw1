package edu.project3.LogWorkers;

import java.time.LocalDateTime;

public record LogRecord(
    String remoteAddr,
    String remoteUser,
    LocalDateTime timeLocal,
    String request,
    int status,
    long bodyBytesSend,
    String httpReferer,
    String httpUserAgent
) {
}
