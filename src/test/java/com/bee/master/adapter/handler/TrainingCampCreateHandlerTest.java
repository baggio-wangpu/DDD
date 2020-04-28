package com.bee.master.adapter.handler;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import com.bee.master.adapter.jpa.entity.TrainingCampTeacherPO;
import com.bee.master.adapter.jpa.repository.TrainingCampJpaRepository;
import com.bee.master.adapter.jpa.repository.TrainingCampTeacherJpaRepository;
import com.bee.master.application.request.CreateTrainingCampRequest;
import com.bee.master.common.BaseAPITest;
import com.bee.master.domain.model.TrainingCampStatus;
import com.gaia.collaborator.command.Command;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.gaia.junit.EventReceiver.emitted;
import static com.gaia.junit.Executor.execute;
import static com.gaia.junit.assertion.EventAssertions.payload;
import static com.gaia.junit.assertion.EventAssertions.type;
import static com.gaia.junit.assertion.EventsAssertions.event;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrainingCampCreateHandlerTest extends BaseAPITest {

    @Autowired
    private TrainingCampJpaRepository trainingCampJpaRepository;

    @Autowired
    private TrainingCampTeacherJpaRepository trainingCampTeacherJpaRepository;

    @AfterEach
    void tearDown() {
        trainingCampJpaRepository.deleteAll();
        trainingCampTeacherJpaRepository.deleteAll();
    }

    @Test
    void should_create_training_camp_success() {
        CreateTrainingCampRequest request = CreateTrainingCampRequest.builder()
                .name("name")
                .clientName("clientName")
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusDays(1))
                .build();
        execute(Command.of()
                .target("/trainingCamps")
                .type("trainingCampCreate")
                .payload(request)
                .createBy("teacherId"));

        emitted(event(type("trainingCampCreate"),
                payload(trainingCampVO -> {
                    assertThat(trainingCampVO.path("$.id"), is(notNullValue()));
                    assertThat(trainingCampVO.path("$.name"), is("name"));
                    assertThat(trainingCampVO.path("$.clientName"), is("clientName"));
                    assertThat(trainingCampVO.path("$.startTime"), is(notNullValue()));
                    assertThat(trainingCampVO.path("$.endTime"), is(notNullValue()));
                    assertThat(trainingCampVO.path("$.status"), is(TrainingCampStatus.ACTIVE.name()));
                })));

        Optional<TrainingCampPO> trainingCampPO = trainingCampJpaRepository.findByName("name");
        assertTrue(trainingCampPO.isPresent());
        assertThat(trainingCampPO.get().getCreatedTime(), is(notNullValue()));
        assertThat(trainingCampPO.get().getLastModifiedTime(), is(notNullValue()));

        Optional<TrainingCampTeacherPO> trainingCampTeacherPO = trainingCampTeacherJpaRepository.findByTeacherId("teacherId");
        assertTrue(trainingCampTeacherPO.isPresent());
    }
}