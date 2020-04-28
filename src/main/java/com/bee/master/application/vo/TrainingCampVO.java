package com.bee.master.application.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.bee.master.domain.model.TrainingCampStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrainingCampVO {

    private Long id;

    private String name;

    private String clientName;

    private TrainingCampStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
