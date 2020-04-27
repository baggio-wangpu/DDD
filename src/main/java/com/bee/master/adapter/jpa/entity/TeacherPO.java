package com.bee.master.adapter.jpa.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bm_teacher")
public class TeacherPO {

  @Id
  private String id;

  @ManyToMany(mappedBy = "teachers")
  @Where(clause = "status = 'ACTIVE'")
  @OrderBy("last_modified_time DESC")
  List<TrainingCampPO> trainingCamps;
}
