package com.bee.master.adapter.jpa.entity;

import com.bee.master.domain.trainingcamp.TrainingCamp;
import com.bee.master.domain.trainingcamp.TrainingCampStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bm_training_camp")
public class TrainingCampPO {

    @Id
    private long id;

    @NotNull
    private String name;

    private String clientName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TrainingCampStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToMany
    @JoinTable(
      name="bm_training_camp_teacher_mapping",
      joinColumns={@JoinColumn(name="trainingCampId")},
      inverseJoinColumns={@JoinColumn(name="teacherId")}
    )
    private List<TeacherPO> teachers;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime lastModifiedTime;

    public TrainingCamp toDomain() {
        return TrainingCamp.fromPO(this.id, this.name, this.clientName, this.status, this.startTime, this.endTime);
    }
}
