package com.bee.master.application.vo;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import com.bee.master.domain.model.TrainingCampStatus;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrainingCampVO {

    private String id;

    private String name;

    private String clientName;

    private Instant startTime;

    private Instant endTime;

    private TrainingCampStatus status;

    private Instant createdTime;

    private Instant lastModifiedTime;
}
