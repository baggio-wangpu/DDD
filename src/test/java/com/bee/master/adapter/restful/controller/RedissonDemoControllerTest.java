package com.bee.master.adapter.restful.controller;

import com.bee.master.common.BaseAPITest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

class RedissonDemoControllerTest extends BaseAPITest {

    @Test
    void should_save_key_value_successful() {
        RestAssured.given()
                .contentType(JSON)
                .body("{\"key\": \"key\", \"value\": \"value\"}")
                .when()
                .post(getBaseUrl("public/keys"))
                .then()
                .statusCode(is(201));
    }

    @Test
    void should_get_value_successful() {
        redissonClient.getBucket("key").set("value");
        RestAssured.given()
                .when()
                .get(getBaseUrl("public/keys/key"))
                .then()
                .statusCode(is(200))
                .body("key", is("key"))
                .body("value", is("value"));
    }
}
