package com.bee.master.adapter.handler.directionalPackage;

import com.bee.master.adapter.jpa.entity.directionalPackage.DirectionalPackagePO;
import com.bee.master.adapter.jpa.repository.directionalPackage.DirectionalPackageJpaRepository;
import com.bee.master.application.request.trainingCamp.CreateDirectionalPackageRequest;
import com.bee.master.common.BaseAPITest;
import com.gaia.collaborator.command.Command;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.gaia.junit.EventReceiver.emitted;
import static com.gaia.junit.Executor.execute;
import static com.gaia.junit.assertion.EventAssertions.payload;
import static com.gaia.junit.assertion.EventAssertions.type;
import static com.gaia.junit.assertion.EventsAssertions.event;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class DirectionalPackageCreateHandlerTest extends BaseAPITest {

  @Autowired
  private DirectionalPackageJpaRepository directionalPackageJpaRepository;

  @AfterEach
  void tearDown() {
    directionalPackageJpaRepository.deleteAll();
  }

  @Test
  void should_create_directional_package_successfully() {
    CreateDirectionalPackageRequest request = CreateDirectionalPackageRequest.builder()
        .name("name")
        .description("description")
        .build();
    execute(Command.of()
        .target("/directionalPackage")
        .type("directionalPackageCreate")
        .payload(request)
        .createBy("userId"));

    emitted(event(type("directionalPackageCreate"),
        payload(directionalPackageVO -> {
          assertThat(directionalPackageVO.path("$.id"), is(notNullValue()));
          assertThat(directionalPackageVO.path("$.name"), is("name"));
          assertThat(directionalPackageVO.path("$.description"), is("description"));
        })));

    List<DirectionalPackagePO> all = directionalPackageJpaRepository.findAll();
    assertThat(all.size(), is(1));
    assertThat(all.get(0).getName(), is("name"));
    assertThat(all.get(0).getDescription(), is("description"));
  }
}