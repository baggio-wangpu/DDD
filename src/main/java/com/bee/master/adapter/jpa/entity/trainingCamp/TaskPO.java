package com.bee.master.adapter.jpa.entity.trainingCamp;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bm_task")
public class TaskPO {

    @Id
    private String id;

    private String name;

    private String trainingCampId;

    private int sortNumber;

    private int visible;
}
