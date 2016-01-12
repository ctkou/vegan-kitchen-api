package builder;

import exception.DatabaseException;
import exception.InvalidDeleteException;
import exception.InvalidUpdateException;
import manager.RecipeManager;
import model.Recipe;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;

/**
 * Created by adam on 21/11/15.
 */
public class RecipeResponseBuilder extends ResponseBuilder {

    private static RecipeManager recipeManager = new RecipeManager();

    public static Response buildGetAllRecipeResponse(int limit, int offset, String orderBy) throws Exception {
        Response response;
        try {
            List<Recipe> recipeList = recipeManager.getRecipes(limit, offset, orderBy);
            response = Response.status(OK).entity(buildResponseBody(CONTENT_RETRIEVAL_SUCCESS, recipeList)).build();
        }
        catch (DatabaseException exception) {
            response = Response.status(BAD_REQUEST).entity(buildResponseBody(CONTENT_RETRIEVAL_FAILURE, exception.getMessage())).build();
        }
        return response;
    }

    public static Response buildGetRecipeByIdResponse(Integer recipeId) throws Exception {
        Response response;
        try {
            Recipe recipe = recipeManager.getRecipeById(recipeId);
            response = Response.status(OK).entity(buildResponseBody(CONTENT_RETRIEVAL_SUCCESS, recipe)).build();
        }
        catch (DatabaseException exception) {
            response = Response.status(BAD_REQUEST).entity(buildResponseBody(CONTENT_RETRIEVAL_FAILURE, exception.getMessage())).build();
        }
        return response;
    }

    public static Response buildPostRecipeResponse(Recipe recipe, SecurityContext securityContext) throws Exception {
        Response response;
        try {
            Recipe createdRecipe = recipeManager.addRecipe(recipe, securityContext);
            response = Response.status(CREATED).entity(buildResponseBody(CONTENT_CREATION_SUCCESS, createdRecipe)).build();
        } catch (DatabaseException exception) {
            response = Response.status(BAD_REQUEST).entity(buildResponseBody(CONTENT_CREATION_FAILURE, exception.getMessage())).build();
        }
        return response;
    }

    public static Response buildPutRecipeResponse(Integer recipeId, Recipe recipeUpdate, SecurityContext securityContext) throws Exception {
        Response response;
        try {
            Recipe updatedRecipe = recipeManager.updateRecipe(recipeId, recipeUpdate, securityContext);
            response = Response.status(OK).entity(buildResponseBody(CONTENT_UPDATE_SUCCESS_MESSAGE, updatedRecipe)).build();
        } catch (DatabaseException exception) {
            response = Response.status(BAD_REQUEST).entity(buildResponseBody(CONTENT_UPDATE_FAIL_MESSAGE, exception.getMessage())).build();
        } catch (InvalidUpdateException exception) {
            response = Response.status(FORBIDDEN).entity(buildResponseBody(CONTENT_UPDATE_FAIL_MESSAGE, exception.getMessage())).build();
        }
        return response;
    }

    public static Response buildDeleteRecipeResponse(Integer recipeId, SecurityContext securityContext) throws Exception {
        Response response;
        try {
            recipeManager.deleteRecipe(recipeId, securityContext);
            response = Response.status(NO_CONTENT).build();
        } catch (DatabaseException exception) {
            response = Response.status(BAD_REQUEST).entity(buildResponseBody(CONTENT_DELETE_FAIL_MESSAGE, exception.getMessage())).build();
        } catch (InvalidDeleteException exception) {
            response = Response.status(FORBIDDEN).entity(buildResponseBody(CONTENT_DELETE_FAIL_MESSAGE, exception.getMessage())).build();
        }
        return response;
    }

}
