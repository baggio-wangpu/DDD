package com.bee.master.domain.trainingcamp;

import com.bee.master.common.utils.SnowFlake;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrainingCamp {

    private long id;

    private String name;

    private String clientName;

    private TrainingCampStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public static TrainingCamp create(String name, String clientName,
                                      LocalDateTime startTime, LocalDateTime endTime) {
        return new TrainingCamp(SnowFlake.nextIdentity(), name, clientName,
          TrainingCampStatus.ACTIVE, startTime, endTime);
    }

    public static TrainingCamp fromPO(long id, String name, String clientName, TrainingCampStatus status, LocalDateTime startTime, LocalDateTime endTime) {
        return new TrainingCamp(id, name, clientName,
          TrainingCampStatus.ACTIVE, startTime, endTime);
    }

    public boolean editAble() {
        return this.getStatus() == TrainingCampStatus.ACTIVE;
    }

    public TrainingCamp update(String name, String clientName, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.clientName = clientName;
        this.startTime = startTime;
        this.endTime = endTime;
        return this;
    }
}