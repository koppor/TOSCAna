package org.opentosca.toscana.core.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.opentosca.toscana.core.parse.InvalidCsarException;
import org.opentosca.toscana.core.transformation.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;

public class CsarUploadErrorResponse {

    @JsonIgnore
    private InvalidCsarException exception;
    @JsonIgnore
    private String path;
    @JsonIgnore
    private long timestamp = System.currentTimeMillis();
    @JsonIgnore
    private int status;

    public CsarUploadErrorResponse(
        InvalidCsarException exception,
        String path,
        int status
    ) {
        this.exception = exception;
        this.path = path;
        this.status = status;
    }

    @JsonProperty("logs")
    public List<LogEntry> getErrorLogs() {
        return new ArrayList<>();
    }

    @JsonProperty("timestamp")
    public long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("status")
    public int getStatus() {
        return status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return exception.getMessage();
    }

    @JsonProperty("exception")
    public String getExceptionClass() {
        return exception.getClass().getName();
    }
}
