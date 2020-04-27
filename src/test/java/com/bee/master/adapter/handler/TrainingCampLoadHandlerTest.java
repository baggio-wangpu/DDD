package com.bee.master.adapter.handler;

import com.bee.master.common.BaseAPITest;
import com.gaia.collaborator.command.Command;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static com.gaia.junit.EventReceiver.emitted;
import static com.gaia.junit.Executor.execute;
import static com.gaia.junit.assertion.EventAssertions.payload;
import static com.gaia.junit.assertion.EventAssertions.type;
import static com.gaia.junit.assertion.EventsAssertions.event;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@Sql({"/sqls/insert_teachers_data.sql"})
class TrainingCampLoadHandlerTest extends BaseAPITest {
  @Test
  void should_add_comment_count_when_related_user_comment_on_product() {
    execute(Command.of()
      .type("trainingCampLoad")
      .createBy("teacher@email.com"));

    emitted(event(
      type("trainingCampLoad"),
      payload(trainingCamps -> {
        MatcherAssert.assertThat(trainingCamps.array("$"), hasSize(2));
        MatcherAssert.assertThat(trainingCamps.path("$[0].name"), is("camp3"));
        MatcherAssert.assertThat(trainingCamps.path("$[1].name"), is("camp1"));
      })
    ));
  }
}
