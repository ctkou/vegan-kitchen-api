package restassured;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import model.Recipe;
import model.RecipeIngredient;
import model.RecipeInstruction;
import org.junit.*;
import org.junit.runners.MethodSorters;
import utility.TestUtility;

import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.*;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * Created by adam on 22/11/15.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecipeResourceTest {

    // URL
    private static final String BASE_URL = "http://localhost:8080/recipe/";

    // schemas
    private static final String RESPONSE_SCHEMA_BASE_PATH = "resources/schemas/recipe_resource_test/";
    private static final String POST_RECIPE_RESPONSE_SCHEMA = RESPONSE_SCHEMA_BASE_PATH + "post_recipe_response_schema.json";
    private static final String PUT_RECIPE_RESPONSE_SCHEMA = RESPONSE_SCHEMA_BASE_PATH + "put_recipe_response_schema.json";

    // test data
    private static final String TEST_DATA_SETUP_SCRIPT = "recipe_resource_test.sql";
    private static final String TEST_USER_ACCOUNTS_FILE = "recipe_resource_test/test_user_accounts.json";
    private static final String POST_RECIPE_JSON_FILE = "recipe_resource_test/post_recipe.json";
    private static final String GET_ALL_RECIPES_JSON_FILE = "recipe_resource_test/get_all_recipes.json";
    private static final String GET_RECIPE_JSON_FILE = "recipe_resource_test/get_recipe.json";
    private static final String PUT_RECIPE_JSON_FILE = "recipe_resource_test/put_recipe.json";

    // test user accounts
    private Map<String, Map<String, String>> userAccounts;

    private static TestUtility testUtility = new TestUtility();

    @BeforeClass
    public static void beforeClassSetUp() throws Exception {
        testUtility.resetTestData(TEST_DATA_SETUP_SCRIPT);
    }

    @Before
    public void setUp() throws Exception {
        userAccounts = testUtility.getPojoFromFile(TEST_USER_ACCOUNTS_FILE, new TypeReference<Map<String, Map<String, String>>>(){});
    }

    @AfterClass
    public static void afterClassTearDown() throws Exception {
        testUtility.resetDataBase();
    }

    @Test
    public void testGetAllRecipes() throws Exception {
        testUtility.resetTestData(TEST_DATA_SETUP_SCRIPT);
        List<Recipe>  actualRecipeList = assertSuccessfulAllRecipesAccess();
        List<Recipe> expectedRecipeList = testUtility.getPojoFromFile(GET_ALL_RECIPES_JSON_FILE, new TypeReference<List<Recipe>>(){});
        for (int i = 0; i < actualRecipeList.size(); i++) {
            assertEqualRecipeContent(expectedRecipeList.get(i), actualRecipeList.get(i));
        }
    }

    @Test
    public void testGetRecipeById() throws Exception {
        Recipe expectedGetRecipe = testUtility.getPojoFromFile(GET_RECIPE_JSON_FILE, new TypeReference<Recipe>(){});
        Recipe actualGetRecipe = assertSuccessfulRecipeAccess(expectedGetRecipe.getRecipeId());
        assertEqualRecipeContent(expectedGetRecipe, actualGetRecipe);
    }

    @Test
    public void testPostRecipe() throws Exception {
        String authorization = login(userAccounts.get("wendy").get("username"), userAccounts.get("wendy").get("password"));
        String expectedPostRecipeJsonString = testUtility.getStringFromFile(POST_RECIPE_JSON_FILE);
        assertSuccessfulRecipeCreation(authorization, expectedPostRecipeJsonString);
    }

    @Test
    public void testPutRecipe() throws Exception {
        String authorization = login(userAccounts.get("wendy").get("username"), userAccounts.get("wendy").get("password"));
        String putRecipeJsonString = testUtility.getStringFromFile(PUT_RECIPE_JSON_FILE);
        assertSuccessPutRecipeUpdate(authorization, 2, putRecipeJsonString);
    }

    private String login(String userName, String password) {
        return given()
            .header("username", userName)
            .header("password", password)
            .contentType(APPLICATION_JSON.toString())
        .when()
            .post("http://localhost:8080/authentication/login")
        .then()
            .assertThat()
                .statusCode(SC_CREATED)
                .extract().path("result");
    }

    private List<Recipe> assertSuccessfulAllRecipesAccess() throws Exception {
        List<Map<String, Object>> recipeMapList =
            when()
                .get(BASE_URL + "all/")
            .then()
                .assertThat()
                    .statusCode(SC_OK)
            .extract()
                .response().path("result");
        return testUtility.convertObjectByReferenceType(recipeMapList, new TypeReference<List<Recipe>>(){});
    }

    private Recipe assertSuccessfulRecipeAccess(int recipeId) throws Exception {
        Map<String, Object> recipeMap =
            when()
                .get(BASE_URL + String.valueOf(recipeId))
            .then()
                .assertThat()
                    .statusCode(SC_OK)
                .extract()
                    .response().path("result");
        return testUtility.convertObjectByReferenceType(recipeMap, new TypeReference<Recipe>(){});
    }

    private Recipe assertSuccessfulRecipeCreation(String authorization, String recipeJsonString) throws Exception {
        Map<String, Object> createdRecipeMap =
            given()
                .header("authorization", authorization)
                .contentType("application/json")
                .body(recipeJsonString)
            .when()
                .post(BASE_URL)
            .then()
                .assertThat()
                    .statusCode(SC_CREATED)
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(POST_RECIPE_RESPONSE_SCHEMA))
                .extract()
                    .response().path("result");
        return testUtility.convertObjectByReferenceType(createdRecipeMap, new TypeReference<Recipe>(){});
    }

    private Recipe assertSuccessPutRecipeUpdate(String authorization, int recipeId, String recipeJsonString) throws Exception {
        Map<String, Object> putRecipeMap =
            given()
                .header("authorization", authorization)
                .contentType("application/json")
                .body(recipeJsonString)
            .when()
                .put(BASE_URL + String.valueOf(recipeId))
            .then()
                .assertThat()
                    .statusCode(SC_OK)
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(PUT_RECIPE_RESPONSE_SCHEMA))
                .extract()
                    .response().path("result");
        return testUtility.convertObjectByReferenceType(putRecipeMap, new TypeReference<Recipe>(){});
    }

    private void assertEqualRecipeContent(Recipe expectedRecipe, Recipe actualRecipe) {
        assertEqualString(expectedRecipe.getDishName(), actualRecipe.getDishName());
        assertEqualString(expectedRecipe.getSummary(), actualRecipe.getSummary());
        assertEqualString(expectedRecipe.getServing(), actualRecipe.getServing());
        assertEqualString(expectedRecipe.getDishImageUrl(), actualRecipe.getDishImageUrl());
        assertEqualInteger(expectedRecipe.getUserId(), actualRecipe.getUserId());
        assertEqualString(expectedRecipe.getUserName(), actualRecipe.getUserName());
        assertEqualInstructionListContent(expectedRecipe.getRecipeInstructionList(), actualRecipe.getRecipeInstructionList());
        assertEqualIngredientListContent(expectedRecipe.getRecipeIngredientList(), actualRecipe.getRecipeIngredientList());
    }

    private void assertEqualInstructionListContent(List<RecipeInstruction> expectedInstructionList, List<RecipeInstruction> actualInstructionList) {
        if (expectedInstructionList.size() != actualInstructionList.size()) {
            Assert.fail("Expected " + expectedInstructionList.size() + " recipe instruction record(s), but found " + actualInstructionList.size());
        }
        for (int i = 0; i < actualInstructionList.size(); i++) {
            assertEqualInstructionContent(expectedInstructionList.get(i), actualInstructionList.get(i));
        }
    }

    private void assertEqualInstructionContent(RecipeInstruction expectedInstruction, RecipeInstruction actualInstruction) {
        assertEqualString(expectedInstruction.getInstruction(), actualInstruction.getInstruction());
        assertEqualInteger(expectedInstruction.getStepNumber(), actualInstruction.getStepNumber());
        assertEqualString(expectedInstruction.getImageUrl(), actualInstruction.getImageUrl());
    }

    private void assertEqualIngredientListContent(List<RecipeIngredient> expectedIngredientList, List<RecipeIngredient> actualIngredientList) {
        if (expectedIngredientList.size() != actualIngredientList.size()) {
            Assert.fail("Expected " + expectedIngredientList.size() + " recipe instruction record(s), but found " + actualIngredientList.size());
        }
        for (int i = 0; i < actualIngredientList.size(); i++) {
            assertEqualIngredientContent(expectedIngredientList.get(i), actualIngredientList.get(i));
        }
    }

    private void assertEqualIngredientContent(RecipeIngredient expectedIngredient, RecipeIngredient actualIngredient) {
        assertEqualString(expectedIngredient.getName(), actualIngredient.getName());
        assertEqualString(expectedIngredient.getAmount(), actualIngredient.getAmount());
        assertEqualString(expectedIngredient.getImageUrl(), actualIngredient.getImageUrl());
    }

    private void assertEqualString(String expectedString, String actualString) {
        if ((expectedString == null && actualString != null) ||
                (expectedString != null && actualString == null) ||
                !expectedString.equals(actualString))
            Assert.fail("Expected " + expectedString + " but found " + actualString);
    }

    private void assertEqualInteger(Integer expectedInteger, Integer actualInteger) {
        if ((expectedInteger != null && actualInteger == null) ||
                (expectedInteger == null && actualInteger != null) ||
                !expectedInteger.equals(actualInteger))
            Assert.fail("Expected " + String.valueOf(expectedInteger) + " but found " + String.valueOf(actualInteger));
    }

}
