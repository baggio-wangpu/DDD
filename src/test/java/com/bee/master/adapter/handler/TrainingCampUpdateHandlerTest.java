package com.bee.master.adapter.handler;

import com.bee.master.adapter.jpa.entity.TrainingCampPO;
import com.bee.master.adapter.jpa.repository.TrainingCampJpaRepository;
import com.bee.master.application.request.UpdateTrainingCampRequest;
import com.bee.master.common.BaseAPITest;
import com.bee.master.domain.model.TrainingCampStatus;
import com.gaia.collaborator.command.Command;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

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

@Sql({"/sqls/insert_training_camp_data.sql"})
class TrainingCampUpdateHandlerTest extends BaseAPITest {

  private static final long ID = 123456789;
  @Autowired
  private TrainingCampJpaRepository trainingCampJpaRepository;

  @AfterEach
  void tearDown() {
    trainingCampJpaRepository.deleteAll();
  }

  @Test
  void should_update_training_camp_success() {
    LocalDateTime startTime = LocalDateTime.now();
    LocalDateTime endTime = startTime.plusDays(1);
    UpdateTrainingCampRequest request = UpdateTrainingCampRequest.builder()
      .id(ID)
      .name("updateName")
      .clientName("updateClientName")
      .startTime(startTime)
      .endTime(endTime)
      .build();
    execute(Command.of()
      .target("/trainingCamps")
      .type("trainingCampUpdate")
      .payload(request)
      .createBy("userId"));

    emitted(event(type("trainingCampUpdate"),
      payload(trainingCampVO -> {
        assertThat(Long.parseLong(trainingCampVO.path("$.id").toString()), is(ID));
        assertThat(trainingCampVO.path("$.name"), is("updateName"));
        assertThat(trainingCampVO.path("$.clientName"), is("updateClientName"));
        assertThat(trainingCampVO.path("$.startTime"), is(notNullValue()));
        assertThat(trainingCampVO.path("$.endTime"), is(notNullValue()));
        assertThat(trainingCampVO.path("$.status"), is(TrainingCampStatus.ACTIVE.name()));
      })));

    Optional<TrainingCampPO> optionalTrainingCampPO = trainingCampJpaRepository.findById(ID);
    assertTrue(optionalTrainingCampPO.isPresent());
    TrainingCampPO trainingCampPO = optionalTrainingCampPO.get();
    assertThat(trainingCampPO.getId(), is(ID));
    assertThat(trainingCampPO.getName(), is("updateName"));
    assertThat(trainingCampPO.getClientName(), is("updateClientName"));
    assertThat(trainingCampPO.getStatus(), is(TrainingCampStatus.ACTIVE));
  }
}
