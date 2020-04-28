package com.bee.master.application.request;

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
public class CreateTrainingCampRequest {

    @NotNull
    private String name;

    private String clientName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
