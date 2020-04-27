package com.bee.master.adapter.restful.controller;

import com.bee.master.adapter.client.UserClient;
import com.bee.master.adapter.jpa.repository.StudentJpaRepository;
import com.bee.master.adapter.jpa.repository.TeacherJpaRepository;
import com.bee.master.application.request.LoginRequest;
import com.bee.master.common.BaseAPITest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.http.ContentType.JSON;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

@Sql({"/sqls/insert_roles_data.sql"})
class TokenControllerTest extends BaseAPITest {

    @Autowired
    @Qualifier("mockedUserClient")
    private UserClient mockedUserClient;

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
        LoginRequest loginRequest = LoginRequest.builder().email("teach@email.com").password("password").build();
        RestAssured.given()
                .contentType(JSON)
                .body(toJson(loginRequest))
                .when()
                .post(getBaseUrl("public/tokens"))
                .then()
                .statusCode(is(200))
                .body("token", is(notNullValue()))
                .body("userInfo", is(notNullValue()))
                .body("userInfo.authorities", is(singletonList("TEACHER")));
    }

    @Test
    void should_get_student_role_successful() {
        LoginRequest loginRequest = LoginRequest.builder().email("student@email.com").password("password").build();
        RestAssured.given()
                .contentType(JSON)
                .body(toJson(loginRequest))
                .when()
                .post(getBaseUrl("public/tokens"))
                .then()
                .statusCode(is(200))
                .body("token", is(notNullValue()))
                .body("userInfo", is(notNullValue()))
                .body("userInfo.authorities", is(singletonList("STUDENT")));
    }

    @Test
    void should_get_both_teacher_student_role_successful() {
        LoginRequest loginRequest = LoginRequest.builder().email("teachstudent@email.com").password("password").build();
        RestAssured.given()
                .contentType(JSON)
                .body(toJson(loginRequest))
                .when()
                .post(getBaseUrl("public/tokens"))
                .then()
                .statusCode(is(200))
                .body("token", is(notNullValue()))
                .body("userInfo", is(notNullValue()))
                .body("userInfo.authorities", is(asList("TEACHER", "STUDENT")));
    }


}