package com.bee.master.adapter.restful.controller;

import com.bee.master.adapter.jpa.repository.RoleRepository;
import com.bee.master.application.vo.RoleVO;
import com.bee.master.common.BaseAPITest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

@Sql({"/sqls/insert_roles_data.sql"})
class JpaDemoControllerTest extends BaseAPITest {

    @Autowired
    RoleRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void should_save_role_successful() {
        RestAssured.given()
                .contentType(JSON)
                .body(toJson(new RoleVO("someTitle", "someDesc")))
                .when()
                .post(getBaseUrl("public/roles"))
                .then()
                .statusCode(is(201))
                .body("id", is(notNullValue()))
                .body("title", is("someTitle"))
                .body("description", is("someDesc"));
    }

    @Test
    void should_get_role_successful() {
        RestAssured.given()
                .when()
                .get(getBaseUrl("public/roles/1"))
                .then()
                .statusCode(is(200))
                .body("id", is(1))
                .body("title", is("testTitle"))
                .body("description", is("testDescription"));
    }
}
