package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class AuthorizationTest {

    @Test
    public void loginEndpointPositive() {
      given().contentType(ContentType.JSON)
      .body("{ \"username\" : \"zuckermans\",\"password\" : \"admin1234\"}")
      .when().post("http://localhost:8080/auth/login")
      .then()
         .statusCode(200);
    }

    @Test
    public void loginEndpointNegative() {
        given().contentType(ContentType.JSON)
        .body("{ \"username\" : \"zuckermans\",\"password\" : \"uhsdfdihsdih\"}")
          .when().post("http://localhost:8080/auth/login")
          .then()
             .statusCode(400);
    }
}
