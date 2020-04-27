package com.bee.master.application.vo;

import lombok.*;

import java.time.Instant;
import java.util.List;

import static java.util.Objects.isNull;

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

}
