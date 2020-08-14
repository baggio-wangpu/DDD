package com.bee.master.adapter.jpa.entity.trainingCamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bm_training_camp_teacher_mapping")
public class TrainingCampTeacherPO {

    @Id
    private long id;

    private long trainingCampId;

    private String teacherId;
}
