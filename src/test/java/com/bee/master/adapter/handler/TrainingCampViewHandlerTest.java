package com.bee.master.adapter.handler;

import com.bee.master.adapter.jpa.repository.TrainingCampJpaRepository;
import com.bee.master.common.BaseAPITest;
import com.bee.master.domain.model.TrainingCampStatus;
import com.gaia.collaborator.command.Command;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static com.gaia.junit.EventReceiver.emitted;
import static com.gaia.junit.Executor.execute;
import static com.gaia.junit.assertion.EventAssertions.payload;
import static com.gaia.junit.assertion.EventAssertions.type;
import static com.gaia.junit.assertion.EventsAssertions.event;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Sql({"/sqls/insert_training_camp_data.sql"})
class TrainingCampViewHandlerTest extends BaseAPITest {

    private static final Long ID = 123456789L;

    @Autowired
    private TrainingCampJpaRepository trainingCampJpaRepository;

    @AfterEach
    void tearDown() {
        trainingCampJpaRepository.deleteAll();
    }

    @Test
    void should_get_training_camp_success() {
        execute(Command.of()
                .target("/trainingCamps")
                .type("trainingCampView")
                .payload(ImmutableMap.of("id", ID))
                .createBy("teacherId"));

        emitted(event(type("trainingCampView"),
                payload(trainingCampVO -> {
                    assertThat(Long.parseLong(trainingCampVO.path("$.id").toString()), is(ID));
                    assertThat(trainingCampVO.path("$.name"), is("name"));
                    assertThat(trainingCampVO.path("$.clientName"), is("clientName"));
                    assertThat(trainingCampVO.path("$.startTime"), is(notNullValue()));
                    assertThat(trainingCampVO.path("$.endTime"), is(notNullValue()));
                    assertThat(trainingCampVO.path("$.status"), is(TrainingCampStatus.ACTIVE.name()));
                })));
    }

}