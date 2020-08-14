package com.bee.master.application.request.trainingCamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTrainingCampRequest {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String clientName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
