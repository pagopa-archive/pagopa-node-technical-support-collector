package it.gov.pagopa.nodetscollector.entities;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "worker")
@RegisterForReflection
public class Worker extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "workerSequence", sequenceName = "worker_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "workerSequence")
    private Long id;

    @Column(unique = true)
    private String nodeId;
    private String endpoint;
}
