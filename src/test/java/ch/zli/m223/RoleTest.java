package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class RoleTest {
    String adminToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2V4YW1wbGUuY29tL2lzc3VlciIsInVwbiI6Inp1Y2tlcm1hbnMiLCJncm91cHMiOlsiQWRtaW4iXSwidXNlcm5hbWUiOiJ6dWNrZXJtYW5zIiwiaWQiOjEsImlhdCI6MTY2NDM2Nzg1OCwiZXhwIjozODExODUxNTA1LCJqdGkiOiJkMzJjMjIxZi0wYTUzLTQ4M2MtYWQ0Mi1hZmMwZmM1YTI2NTgifQ.Xv13gZTVbMGL6UfYx4CqnqsAnH1MkLhZPzqqgvKUoIp1Tco9SAxv1q7LS3Vv91ouaAdGfgrYL-NqQy_GuNifkmsIpK26nmhUhBUvXWyqWgZ68o7kWCMzzQT7Qygl8mWzuz7MGkNy6eBH3eOTQZyRfiktbhFp_9iUz3ZdiAZscK9fuyzshUUPmqc7VT9QsLKTcemud7eG8fEgr_VXTrNvWjs9B2O8p7FS5kAT4Hq6Cy5gq2lz765E2m57cWV1vxlf2ziyIoHJ8ZBE09Nc_DujPPtWirWUpoD76bBFMjrD8YdW2yW-WgoSWY3bWby9H4Il89XdV7toPwLctJGrXaXEgQ";
    String mitgliedToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2V4YW1wbGUuY29tL2lzc3VlciIsInVwbiI6InNwYXR6aiIsImdyb3VwcyI6WyJNaXRnbGllZCJdLCJ1c2VybmFtZSI6InNwYXR6aiIsImlkIjoyLCJpYXQiOjE2NjQzNjc5OTksImV4cCI6MzgxMTg1MTY0NiwianRpIjoiYzNiMTc4MjYtNGQ0MC00MTdiLWJiOGUtMzQwZGVmMDI3NmUzIn0.klUTzigJxG9AonycMt2yu5qTpys5vp3zppioZsq0quUdYxnhvuoyyLgsf6HfmJXMNnUdfgipmGg-o9IWqFo8kYcx8EZUorBovHKBX2l1KVcbr8RiGWWwk15qTThsY0013wuijd4t-26Z0a5Kbgpi-tDmaKnlaQWdCVjP0ZlGzgdnHqdWgaqvmK9_S8f-OKI0sLWnGUGilKBZmZRlYABFogBRBXOp-2Lk0DRXx5Wg799umj8NnZmQMfTfb9bl-84T1-7nLIQT3I4AGOacTgEUGUVqDcrv6oCpuGJSltd5-vwFIIT34N0F2ZCTm1yndem5Q4g2LrSV9SzPhSDqbOyNAA";

    @Test
    public void roleEndpointPositive() {
      given().contentType(ContentType.JSON)
      .header("Authorization", "Bearer "+adminToken)
      .body("{\"name\":\"test\"}")
      .when().post("http://localhost:8080/roles")
      .then()
         .statusCode(200);
    }

    @Test
    public void loginEndpointNegative() {
        given().contentType(ContentType.JSON)
          .when().delete("http://localhost:8080/roles/1")
          .then()
             .statusCode(401);
    }
}
