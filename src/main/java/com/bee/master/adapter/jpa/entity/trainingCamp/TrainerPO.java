package com.bee.master.adapter.jpa.entity.trainingCamp;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class TrainerPO {

  @Id
  private String id;

  private String name;

  private String avatar;

  @ManyToMany(mappedBy = "trainers")
  @OrderBy("last_modified_time DESC")
  List<TrainingCampPO> trainingCamps;
}
