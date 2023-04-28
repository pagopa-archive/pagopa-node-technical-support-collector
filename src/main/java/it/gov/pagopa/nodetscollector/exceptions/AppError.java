package it.gov.pagopa.nodetscollector.exceptions;

import lombok.Getter;


@Getter
public enum AppError {

    WORKER_NOT_FOUND(404, "Not found", "Worker with id %s not found"),
    WORKER_CONFLICT(409, "Conflict", "A worker with the nodeId %s already exists"),
    INTERNAL_ERROR(500, "Internal error", "Processing error"),
    UNKNOWN(520, null, null);


    public final int httpStatus;
    public final String title;
    public final String details;


    AppError(int httpStatus, String title, String details) {
        this.httpStatus = httpStatus;
        this.title = title;
        this.details = details;
    } 
}
