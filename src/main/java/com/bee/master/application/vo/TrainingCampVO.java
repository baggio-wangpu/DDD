package com.bee.master.application.vo;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCampVO {

    private String id;

    private String name;

    private String clientName;

    private Instant startDateTime;

    private Instant endDateTime;

    private String status;

    private Instant createdTime;

    private Instant lastModifiedTime;

    public static TrainingCampVO init(TrainingCampPO product) {
        return buildBy(product);
    }

    public static TrainingCampVO buildBy(TrainingCampPO trainingCampPO) {
        return TrainingCampVO.builder()
                .id(trainingCampPO.getId())
                .name(trainingCampPO.getName())
                .clientName(trainingCampPO.getClientName())
                .status(trainingCampPO.getStatus())
                .startDateTime(trainingCampPO.getStartDateTime())
                .endDateTime(trainingCampPO.getEndDateTime())
                .lastModifiedTime(trainingCampPO.getLastModifiedTime())
                .createdTime(trainingCampPO.getCreatedTime())
                .build();
    }

}
