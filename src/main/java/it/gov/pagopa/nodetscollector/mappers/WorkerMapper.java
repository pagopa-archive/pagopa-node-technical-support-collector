package it.gov.pagopa.nodetscollector.mappers;

import it.gov.pagopa.nodetscollector.entities.Worker;
import it.gov.pagopa.nodetscollector.models.requests.WorkerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface WorkerMapper {
    @Mapping(target = "nodeId", source = "nodeId")
    @Mapping(target = "endpoint", source = "endpoint")
    Worker toEntity(WorkerRequest workerRequest);
}
