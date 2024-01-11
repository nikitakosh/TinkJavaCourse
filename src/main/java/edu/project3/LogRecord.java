package edu.project3;

import java.time.LocalDateTime;

public record LogRecord(String remoteAddr, String remoteUser, LocalDateTime timeLocal, String request, int status,
                        int bodyBytesSent, String httpRefer, String httpUserAgent, String requestType) {
}
