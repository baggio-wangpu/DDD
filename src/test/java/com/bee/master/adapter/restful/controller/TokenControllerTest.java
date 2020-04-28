package com.bee.master.adapter.restful.controller;

import com.bee.master.adapter.client.UserClient;
import com.bee.master.adapter.jpa.repository.StudentJpaRepository;
import com.bee.master.adapter.jpa.repository.TeacherJpaRepository;
import com.bee.master.application.request.LoginRequest;
import com.bee.master.application.vo.LoginVO;
import com.bee.master.application.vo.UserVO;
import com.bee.master.common.BaseAPITest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.http.ContentType.JSON;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Sql({"/sqls/insert_roles_data.sql"})
class TokenControllerTest extends BaseAPITest {

  @Autowired
  private UserClient userClient;

  @Autowired
  private TeacherJpaRepository teacherJpaRepository;

  @Autowired
  private StudentJpaRepository studentJpaRepository;

  @AfterEach
  void tearDown() {
    teacherJpaRepository.deleteAll();
    studentJpaRepository.deleteAll();
  }

  @Test
  void should_get_teacher_role_successful() {
    LoginRequest loginRequest = new LoginRequest("teach@email.com", "password");
    mockUserAs("teach@email.com");

    RestAssured.given()
      .contentType(JSON)
      .body(toJson(loginRequest))
      .when()
      .post(getBaseUrl("tokens"))
      .then()
      .statusCode(is(200))
      .body("token", is(notNullValue()))
      .body("userInfo", is(notNullValue()))
      .body("userInfo.authorities", is(singletonList("TEACHER")));
  }

  @Test
  void should_get_student_role_successful() {
    LoginRequest loginRequest = new LoginRequest("student@email.com", "password");
    mockUserAs("student@email.com");

    RestAssured.given()
      .contentType(JSON)
      .body(toJson(loginRequest))
      .when()
      .post(getBaseUrl("tokens"))
      .then()
      .statusCode(is(200))
      .body("token", is(notNullValue()))
      .body("userInfo", is(notNullValue()))
      .body("userInfo.authorities", is(singletonList("STUDENT")));
  }

  @Test
  void should_get_both_teacher_student_role_successful() {
    LoginRequest loginRequest = new LoginRequest("teachstudent@email.com", "password");
    mockUserAs("teachstudent@email.com");

    RestAssured.given()
      .contentType(JSON)
      .body(toJson(loginRequest))
      .when()
      .post(getBaseUrl("tokens"))
      .then()
      .statusCode(is(200))
      .body("token", is(notNullValue()))
      .body("userInfo", is(notNullValue()))
      .body("userInfo.authorities", is(asList("TEACHER", "STUDENT")));
  }

  private void mockUserAs(String email) {
    when(userClient.login(any()))
      .thenReturn(new LoginVO("token", UserVO.builder()
        .id(email)
        .email(email)
        .build()));
  }
}
