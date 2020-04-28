package com.bee.master.adapter.jpa.entity;

import com.bee.master.domain.trainingcamp.TrainingCamp;
import com.bee.master.domain.trainingcamp.TrainingCampStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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

    @OneToMany
    @JoinTable(
            name="bm_task",
            joinColumns={@JoinColumn(name="trainingCampId")},
            inverseJoinColumns={@JoinColumn(name="id")}
    )
    private List<TaskPO> tasks;

    @Formula(value="(select COUNT(*) from bm_training_camp_student_mapping ts where ts.training_camp_id = id)")
    private int studentCount;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime lastModifiedTime;

    public TrainingCamp toDomain() {
        return TrainingCamp.fromPO(this.id, this.name, this.clientName, this.status, this.startTime, this.endTime);
    }
}
