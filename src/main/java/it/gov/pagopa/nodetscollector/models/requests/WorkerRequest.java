package it.gov.pagopa.nodetscollector.models.requests;


import lombok.Getter;

@Getter
public class WorkerRequest {
    private String nodeId;
    private String endpoint;
}
