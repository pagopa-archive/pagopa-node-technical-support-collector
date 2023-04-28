package it.gov.pagopa.nodetscollector.services;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import it.gov.pagopa.nodetscollector.entities.Worker;
import it.gov.pagopa.nodetscollector.exceptions.AppError;
import it.gov.pagopa.nodetscollector.exceptions.AppException;
import it.gov.pagopa.nodetscollector.mappers.WorkerMapper;
import it.gov.pagopa.nodetscollector.models.requests.WorkerRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class WorkerService {

    @Inject
    WorkerMapper workerMapper;

    public List getWorkers() {
        return Worker.listAll();
    }

    @Transactional
    public Worker createWorker(WorkerRequest workerRequest) {
        Optional<Worker> optWorker = Worker
                .find("nodeId", workerRequest.getNodeId())
                .firstResultOptional();
        if (optWorker.isPresent()) {
            throw new AppException(AppError.WORKER_CONFLICT, workerRequest.getNodeId());
        }

        Worker worker = workerMapper.toEntity(workerRequest);
        worker.persist();
        return worker;
    }

    public Worker getWorker(Long id) {
        Optional<Worker> optWorker = Worker.findByIdOptional(id);
        if (optWorker.isEmpty()) {
            throw new AppException(AppError.WORKER_NOT_FOUND, id);
        }
        return optWorker.get();
    }

    public void deleteWorker(Long id) {
        Optional<Worker> optWorker = Worker.findByIdOptional(id);
        if (optWorker.isEmpty()) {
            throw new AppException(AppError.WORKER_NOT_FOUND, id);
        }
        optWorker.get().delete();
    }

}
