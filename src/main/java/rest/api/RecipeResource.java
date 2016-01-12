package rest.api;

import application.filter.Authentication;
import builder.RecipeResponseBuilder;
import model.Recipe;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("recipe")
public class  RecipeResource {

    @GET
    @Path("all")
    @Produces("application/json")
    public Response getAllRecipe(@DefaultValue("10") @QueryParam("limit") int limit,
                                 @DefaultValue("0") @QueryParam("offset") int offset,
                                 @DefaultValue("dish_name") @QueryParam("order_by") String orderBy) throws Exception{
        return RecipeResponseBuilder.buildGetAllRecipeResponse(limit, offset, orderBy);
    }

    @GET
    @Path("{recipe_id}")
    @Produces("application/json")
    public Response getRecipeById(@PathParam("recipe_id") int recipeId) throws Exception {
        return RecipeResponseBuilder.buildGetRecipeByIdResponse(recipeId);
    }

    @POST
    @Authentication
    @Consumes("application/json")
    @Produces("application/json")
    public Response createRecipe(Recipe recipe, @Context SecurityContext securityContext) throws Exception {
        return RecipeResponseBuilder.buildPostRecipeResponse(recipe, securityContext);
    }

    @PUT
    @Authentication
    @Path("{recipe_id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response putRecipe(@PathParam("recipe_id") Integer recipeId, Recipe recipeUpdate, @Context SecurityContext securityContext) throws Exception {
        return RecipeResponseBuilder.buildPutRecipeResponse(recipeId, recipeUpdate, securityContext);
    }

    @DELETE
    @Authentication
    @Path("{recipe_id}")
    public Response deleteRecipe(@PathParam("recipe_id") Integer recipeId, @Context SecurityContext securityContext) {
        securityContext.getUserPrincipal();
        return null; // TODO
    }

}