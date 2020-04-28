package com.bee.master.domain.trainingcamp;

import com.bee.master.domain.model.TrainingCampStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCamp {

    private long id;

    private String name;

    private String clientName;

    private TrainingCampStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


    public static TrainingCamp create(String name, String clientName,
                                      LocalDateTime startTime, LocalDateTime endTime) {
        return TrainingCamp.builder()
                .name(name)
                .clientName(clientName)
                .startTime(startTime)
                .endTime(endTime)
                .status(TrainingCampStatus.ACTIVE)
                .build();
    }

    public boolean editAble() {
        return this.getStatus() == TrainingCampStatus.ACTIVE;
    }

    public TrainingCamp update(String name, String clientName, LocalDateTime startTime, LocalDateTime endTime) {
        this.setName(name);
        this.setClientName(clientName);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        return this;
    }
}
