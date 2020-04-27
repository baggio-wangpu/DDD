package com.bee.master.adapter.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Setter
@Entity
@Table(name = "bm_training_camp")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainingCampPO {

    @Id
    @Column(name = "ID")
    private String id;

    @NotNull
    @Column(name = "name", length = 64)
    private String name;

    @NotNull
    @Column(name = "client_name", length = 64)
    private String clientName;

    @NotNull
    @Column(name = "status", length = 64)
    private String status;

    @Column(name = "start_time")
    private Instant startDateTime;

    @Column(name = "created_time")
    private Instant endDateTime;

    @Column(name = "end_time")
    private Instant createdTime;

    @Column(name = "last_modified_time")
    private Instant lastModifiedTime;
}
