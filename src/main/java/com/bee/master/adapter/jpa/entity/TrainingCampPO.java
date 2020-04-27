package com.bee.master.adapter.jpa.entity;

import com.bee.master.domain.model.TrainingCampStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OrderBy;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bm_training_camp")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCampPO {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String clientName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TrainingCampStatus status;

    private Instant startTime;

    private Instant endTime;

    private Instant createdTime;

    private Instant lastModifiedTime;

    @ManyToMany
    @JoinTable(
      name="bm_training_camp_teacher_mapping",
      joinColumns={@JoinColumn(name="training_camp_id")},
      inverseJoinColumns={@JoinColumn(name="teacher_id")}
    )
    private List<TeacherPO> teachers;
}
