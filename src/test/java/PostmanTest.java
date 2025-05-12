import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class PostmanTest {
    @Test
    @DisplayName("GET request")
    public void testGetRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .when()
                .get("/?foo1=bar1&foo2=bar2")
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    @DisplayName("POST Raw Text")
    public void testPostRequestWithBody() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
    @Test
    @DisplayName("POST Form Data")
    public void testPostWithFormData() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("multipart/form-data")
                .multiPart("fool", "bari")
                .multiPart("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.fool", equalTo("bari"))
                .body("form.foo2", equalTo("bar2"));
    }

    @Test
    @DisplayName("PUT Request")
    public void testPutRequestWithTextBody() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    @Test
    @DisplayName("PATCH request")
    public void testPatchRequestWithBody() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }
    @Test
    @DisplayName("DELETE Request")
    public void testDeleteRequestWithBody() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

}




