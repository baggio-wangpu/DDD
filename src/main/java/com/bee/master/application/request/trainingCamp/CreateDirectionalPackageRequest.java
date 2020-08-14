package com.bee.master.application.request.trainingCamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDirectionalPackageRequest {

    @NotNull
    private String name;
    private String description;
}
