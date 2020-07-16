import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SimpleTest {

    @Test
    public void getBookstore() {
        RestAssured.baseURI = "http://bookstore.toolsqa.com";

        RequestSpecification httpRequest = RestAssured.given();
//        Response response = httpRequest.request(Method.GET, "BookStore/v1/Books");
        httpRequest.request(Method.GET, "BookStore/v1/Books")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jasonSchema.json"));
    }

    @Test
    public void useQueryParameter() {
        RestAssured.baseURI = "https://samples.openweathermap.org/data/2.5/";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.queryParam("q", "Vilnius")
                .queryParam("appid", "7c97df50efa1652f177914b86bfded32")
                .get("weather");

        System.out.println(response.statusCode());

//        Assertions.assertEquals("London", response.getBody());
        response.prettyPrint();

    }


    //        int statusCode = response.getStatusCode();
//        System.out.println(statusCode);
//
////        Headers header = response.getHeaders();
////        System.out.println(header);
//
////        ResponseBody body = response.getBody();
////        System.out.println(body.prettyPrint());
//
//        String type = response.getContentType();
//        System.out.println(type);
//
//        String status = response.getStatusLine();
//        System.out.println(status);

//        Assertions.assertEquals(200, response.getStatusCode(), "incorrect status code");

    @Test
    public void createUser() {
        RestAssured.baseURI = "http://bookstore.toolsqa.com/";

        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

        String payload = "{\n" +
                "  \"userName\": \"newUser2\",\n" +
                "  \"password\": \"Password1!\"\n" +
                "}";

        Response response = request.body(payload).post("/Account/v1/User");
        int statusCode = response.getStatusCode();

        System.out.println(statusCode);

        response.prettyPrint();

    }

    @Test
    public void authenticationTest() {
        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

        RequestSpecification request = RestAssured.given();
//        Username : password = ToolsQA:TestPassword

        String credentials = "ToolsQA:TestPassword";
        byte[] encodedCredential = Base64.encodeBase64(credentials.getBytes());
        String encodedCredentialsString = new String(encodedCredential);

        request.header("Authorization", "Basic " + encodedCredentialsString );

        Response response = request.get();
        System.out.println(response.getStatusCode());
        response.prettyPrint();
    }


    String baseURI = "http://bookstore.toolsqa.com/";
    String isbn = "9781449331818";
    String userID = "466462f1-a284-4c59-8421-57176febde5e";

    @Test
    public void authorizationTest(){
        RestAssured.baseURI = baseURI;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        String payload = "{\n" +
                "  \"userName\": \"testuser11\",\n" +
                "  \"password\": \"Testpass1!\"\n" +
                "}";
        Response response = request.body(payload).post("/Account/v1/GenerateToken");
        Assertions.assertEquals(200,response.getStatusCode());
//        System.out.println("response body" + response.getBody().asString());
        response.prettyPrint();

        String jsonString = response.asString();
        Assertions.assertTrue(jsonString.contains("token"));

//        String token = JsonPath.
    }

    @Test
    public void postTweet(){
        Response response = RestAssured.given().auth().oauth("", "", "", "").post("http ");

    }

    @Test
    public void getAuthToken(){
        Response response = RestAssured.given()
                .formParam("client_id", "Best App EVER")
                .formParam("client_secret", "d93b340f78168a61e4d1dd579fc4b5cc")
                .formParam("grant_type", "client_credentials")
                .post("http://coop.apps.symfonycasts.com/token");

        response.prettyPrint();
    }

    @Test
    public void puSeatDown(){
        Response response = RestAssured.given()
                .formParam("client_id", "Best App EVER")
                .formParam("client_secret", "d93b340f78168a61e4d1dd579fc4b5cc")
                .formParam("grant_type", "client_credentials")
                .post("http://coop.apps.symfonycasts.com/token");
        String token = response.jsonPath().get("access_token");

        String userID = "1169";

        Response rest = RestAssured.given()
                .auth()
                .oauth2(token)
                .post("http://coop.apps.symfonycasts.com/api/1169/barn-unlock");

        System.out.println(rest.getStatusCode());
        rest.prettyPrint();
    }

        @Test
        public void changeBook(){
            RestAssured.baseURI = "https://bookstore.toolsqa.com";
            String isbn = "9781449325862";
            String isbn2 = "9781449331818";
            String userID = "d9f26e67-8f17-4ac7-8a1f-7c54f58fb773";

            RequestSpecification request = RestAssured.given();

            request.header("Content-Type", "application/json")
                    .header("Authorization", "Basic dGVzdFVzZXIyMjp0ZXN0VXNlcjIyIQ==");

            String payload = "{\n" +
                    "  \"userId\": \"d9f26e67-8f17-4ac7-8a1f-7c54f58fb773\",\n" +
                    "  \"isbn\": \"9781449331818\"\n" +
                    "}";
            Response response = request.body(payload).put("/BookStore/v1/Books/"+isbn);
//            Assertions.assertEquals(200,response.getStatusCode());
//        System.out.println("response body" + response.getBody().asString());
            response.prettyPrint();

//            String jsonString = response.asString();
//            Assertions.assertTrue(jsonString.contains("token"));

//        String token = JsonPath.
        }

    @Test
    public void deleteBook(){
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        String isbn = "9781449325862";
        String isbn2 = "9781449331818";
        String userID = "d9f26e67-8f17-4ac7-8a1f-7c54f58fb773";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json")
                .header("Authorization", "Basic dGVzdFVzZXIyMjp0ZXN0VXNlcjIyIQ==");

        String payload = "{\n" +
                "  \"isbn\": \"9781449331818\",\n" +
                "  \"userId\": \"d9f26e67-8f17-4ac7-8a1f-7c54f58fb773\"\n" +
                "}";
        Response response = request.body(payload).delete("/BookStore/v1/Book");
//            Assertions.assertEquals(200,response.getStatusCode());
//        System.out.println("response body" + response.getBody().asString());
        response.prettyPrint();

//            String jsonString = response.asString();
//            Assertions.assertTrue(jsonString.contains("token"));

//        String token = JsonPath.
    }
    }





