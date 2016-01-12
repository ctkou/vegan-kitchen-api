package restassured;

import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utility.TestUtility;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.*;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by adam on 25/12/15.
 */
public class AuthenticationResourceTest {

    // URL
    private static final String BASE_URL = "http://localhost:8080/authentication/";

    // test data
    private static final String USER_SIGNUP_JSON_FILE = "authentication_resource_test/signup.json";

    // schemas
    private static final String RESPONSE_SCHEMA_BASE_PATH = "resources/schemas/authentication_resource_test/";
    private static final String LOGIN_RESPONSE_SCHEMA_CREATED = RESPONSE_SCHEMA_BASE_PATH + "login_response_schema_CREATED.json";
    private static final String LOGIN_RESPONSE_SCHEMA_FORBIDDEN = RESPONSE_SCHEMA_BASE_PATH + "login_response_schema_FORBIDDEN.json";
    private static final String SIGNUP_RESPONSE_SCHEMA_CREATED = RESPONSE_SCHEMA_BASE_PATH + "signup_response_schema_CREATED.json";
    private static final String SIGNUP_RESPONSE_SCHEMA_INVALID_PASSWORD = RESPONSE_SCHEMA_BASE_PATH + "signup_response_schema_INVALID_PASSWORD.json";
    private static final String SIGNUP_RESPONSE_SCHEMA_INVALID_MAILBOX = RESPONSE_SCHEMA_BASE_PATH + "signup_response_schema_INVALID_MAILBOX.json";
    private static final String SIGNUP_RESPONSE_SCHEMA_DUPLICATE_USER = RESPONSE_SCHEMA_BASE_PATH + "signup_response_schema_DUPLICATE_USER.json";

    // test configuration
    private static final int USER_NAME_LENGTH = 15;
    private static final int VALID_PASSWORD_LENGTH = 10;
    private static final int INVALID_PASSWORD_LENGTH = 8;
    private static final int MAILBOX_LENGTH = 15;

    private static TestUtility testUtility = new TestUtility();

    @BeforeClass
    public static void setUp() throws Exception {
        testUtility.resetDataBase();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        testUtility.resetDataBase();
    }

    @Test
    public void testSuccessfulLogin() throws Exception {
        Map<String, String> userAccount = getNewUserAccount();
        String jsonResponseAsString = assertLoginResponse(userAccount.get("username"), userAccount.get("password"), SC_CREATED).asString();
        assertThat(jsonResponseAsString, matchesJsonSchemaInClasspath(LOGIN_RESPONSE_SCHEMA_CREATED));
    }

    @Test
    public void testLoginWithWrongPassword() throws Exception {
        Map<String, String> userAccount = getNewUserAccount();
        String wrongPassword = testUtility.getRandomString(INVALID_PASSWORD_LENGTH);
        String jsonResponseAsString = assertLoginResponse(userAccount.get("username"), wrongPassword, SC_FORBIDDEN).asString();
        assertThat(jsonResponseAsString, matchesJsonSchemaInClasspath(LOGIN_RESPONSE_SCHEMA_FORBIDDEN));
    }

    @Test
    public void testLoginWithWrongUserName() throws Exception {
        Map<String, String> userAccount = getNewUserAccount();
        String wrongUserName = testUtility.getRandomString(USER_NAME_LENGTH);
        String jsonResponseAsString = assertLoginResponse(wrongUserName, userAccount.get("password"), SC_FORBIDDEN).asString();
        assertThat(jsonResponseAsString, matchesJsonSchemaInClasspath(LOGIN_RESPONSE_SCHEMA_FORBIDDEN));
    }

    @Test
    public void testLogout() throws Exception {
        Map<String, String> userAccount = getNewUserAccount();
        Map<String, Object> response = assertLoginResponse(userAccount.get("username"), userAccount.get("password"), SC_CREATED).path("");
        String authorization = (String) response.get("result");
        assertLogoutResponse(authorization, SC_NO_CONTENT);
    }

    @Test
    public void testSuccessfulSignup() throws Exception {
        String userName = testUtility.getRandomString(USER_NAME_LENGTH);
        String password = testUtility.getRandomString(VALID_PASSWORD_LENGTH);
        String email = testUtility.getRandomString(MAILBOX_LENGTH) + "@test.com";
        String jsonResponseAsString = assertSignupResponse(buildSignupContent(userName, password, email), SC_CREATED).asString();
        assertThat(jsonResponseAsString, matchesJsonSchemaInClasspath(SIGNUP_RESPONSE_SCHEMA_CREATED));
    }

    @Test
    public void testSignupWithInvalidPassword() throws Exception {
        String userName = testUtility.getRandomString(USER_NAME_LENGTH);
        String password = testUtility.getRandomString(INVALID_PASSWORD_LENGTH);
        String email = testUtility.getRandomString(MAILBOX_LENGTH) + "@test.com";
        String jsonResponseAsString = assertSignupResponse(buildSignupContent(userName, password, email), SC_BAD_REQUEST).asString();
        assertThat(jsonResponseAsString, matchesJsonSchemaInClasspath(SIGNUP_RESPONSE_SCHEMA_INVALID_PASSWORD));
    }

    @Test
    public void testSignupWithInvalidMailbox() throws Exception {
        String userName = testUtility.getRandomString(USER_NAME_LENGTH);
        String password = testUtility.getRandomString(VALID_PASSWORD_LENGTH);
        String email = testUtility.getRandomString(MAILBOX_LENGTH) + "test.com";
        String jsonResponseAsString = assertSignupResponse(buildSignupContent(userName, password, email), SC_BAD_REQUEST).asString();
        assertThat(jsonResponseAsString, matchesJsonSchemaInClasspath(SIGNUP_RESPONSE_SCHEMA_INVALID_MAILBOX));
    }

    @Test
    public void testSignupWithSameUserInformationTwice() throws Exception {
        String userName = testUtility.getRandomString(USER_NAME_LENGTH);
        String password = testUtility.getRandomString(VALID_PASSWORD_LENGTH);
        String email = testUtility.getRandomString(MAILBOX_LENGTH) + "@test.com";
        String signupContent = buildSignupContent(userName, password, email);
        assertSignupResponse(signupContent, SC_CREATED);
        String jsonResponseAsString = assertSignupResponse(signupContent, SC_BAD_REQUEST).asString();
        assertThat(jsonResponseAsString, matchesJsonSchemaInClasspath(SIGNUP_RESPONSE_SCHEMA_DUPLICATE_USER));
    }

    private String buildSignupContent(String userName, String password, String email) throws Exception {
        return testUtility.getStringFromFile(USER_SIGNUP_JSON_FILE)
            .replaceAll("USER_NAME", userName)
            .replaceAll("PASSWORD", password)
            .replaceAll("EMAIL_ADDRESS", email);
    }

    private Map<String, String> getNewUserAccount() throws Exception {
        Map<String, String> userAccount = new HashMap<>();
        userAccount.put("username", testUtility.getRandomString(USER_NAME_LENGTH));
        userAccount.put("password", testUtility.getRandomString(VALID_PASSWORD_LENGTH));
        userAccount.put("mailbox", testUtility.getRandomString(MAILBOX_LENGTH) + "@test.com");
        assertSignupResponse(buildSignupContent(userAccount.get("username"), userAccount.get("password"), userAccount.get("mailbox")), SC_CREATED);
        return userAccount;
    }

    private ResponseBodyExtractionOptions assertSignupResponse(String signupContent, int expectedStatusCode) {
        return given()
            .contentType(APPLICATION_JSON.toString())
            .body(signupContent)
        .when()
            .post(BASE_URL + "signup")
        .then()
            .assertThat()
                .statusCode(expectedStatusCode)
            .extract()
                .body();
    }

    private ResponseBodyExtractionOptions assertLoginResponse(String userName, String password, int expectedStatusCode) {
        return given()
            .header("username", userName)
            .header("password", password)
            .contentType(APPLICATION_JSON.toString())
        .when()
            .post(BASE_URL + "login")
        .then()
            .assertThat()
                .statusCode(expectedStatusCode)
            .extract().body();
    }

    private String assertLogoutResponse(String authorization, int expectedStatusCode) {
        return given()
            .header("Authorization", authorization)
            .contentType(APPLICATION_JSON.toString())
        .when()
            .post(BASE_URL + "logout")
        .then()
            .assertThat()
                .statusCode(expectedStatusCode)
            .extract().body().asString();
    }

}
