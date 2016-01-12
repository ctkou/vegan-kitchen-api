package manager;

import exception.DatabaseException;
import exception.InvalidUpdateException;
import finder.RecipeFinder;
import model.Recipe;
import model.User;
import org.jooq.exception.DataAccessException;

import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by wendywang on 2015-11-08.
 */

public class RecipeManager {

    private RecipeFinder recipeFinder = new RecipeFinder();

    private UserManager userManager = new UserManager();
    private RecipeInstructionManager instructionManager = new RecipeInstructionManager();
    private RecipeIngredientManager ingredientManager = new RecipeIngredientManager();

    public List<Recipe> getRecipes(int limit, int offset, String orderBy) throws DatabaseException, IOException {
        List<Recipe> recipeList = recipeFinder.getRecipes(limit, offset, orderBy);
        attachRecipeInstruction(recipeList);
        attachRecipeIngredient(recipeList);
        return recipeList;
    }

    private void attachRecipeInstruction(List<Recipe> recipeList) throws DatabaseException {
        for (Recipe recipe: recipeList) {
            recipe.setRecipeInstructionList(instructionManager.getInstructionListByRecipeId(recipe.getRecipeId()));
        }
    }

    private void attachRecipeIngredient(List<Recipe> recipeList) throws DatabaseException {
        for (Recipe recipe: recipeList) {
            recipe.setRecipeIngredientList(ingredientManager.getIngredientListByRecipeId(recipe.getRecipeId()));
        }
    }

    public Recipe getRecipeById(int recipeId) throws DatabaseException {
        Recipe recipe = recipeFinder.findRecipeById(recipeId);
        attachRecipeInstruction(recipe);
        attachRecipeIngredient(recipe);
        return recipe;
    }

    private void attachRecipeInstruction(Recipe recipe) throws DatabaseException {
        recipe.setRecipeInstructionList(instructionManager.getInstructionListByRecipeId(recipe.getRecipeId()));
    }

    private void attachRecipeIngredient(Recipe recipe) throws DatabaseException {
        recipe.setRecipeIngredientList(ingredientManager.getIngredientListByRecipeId(recipe.getRecipeId()));
    }

    public Recipe addRecipe(Recipe recipe, SecurityContext securityContext) throws DatabaseException {
        User user = userManager.getUser(securityContext.getUserPrincipal().getName());
        recipe.setUserId(user.getUserId());
        recipe.setUserName(user.getUserName());
        Integer createdRecipeId = recipeFinder.storeRecipe(recipe);
        instructionManager.addRecipeInstructionList(createdRecipeId, recipe.getRecipeInstructionList());
        ingredientManager.addRecipeIngredientList(createdRecipeId, recipe.getRecipeIngredientList());
        return getRecipeById(createdRecipeId);
    }

    public Recipe updateRecipe(Integer recipeId, Recipe recipeUpdate, SecurityContext securityContext) throws DatabaseException, InvalidUpdateException {
        if (isValidRecipeUpdate(recipeId, recipeUpdate, securityContext)) {
            recipeFinder.updateRecipe(recipeUpdate);
            instructionManager.putRecipeInstructionList(recipeUpdate.getRecipeInstructionList());
            ingredientManager.putRecipeIngredientList(recipeUpdate.getRecipeIngredientList());
        }
        return getRecipeById(recipeId);
    }

    private boolean isValidRecipeUpdate(Integer recipeId, Recipe recipeUpdate, SecurityContext securityContext) throws InvalidUpdateException {
        try {
            Recipe existingRecipe = getRecipeById(recipeId);
            if (!existingRecipe.getUserName().equals(securityContext.getUserPrincipal().getName())) {
                throw new InvalidUpdateException(InvalidUpdateException.getInvalidUpdateDataMessage("lack permission to update requested recipe"));
            }
            else if (!containValidRecipeId(recipeId, recipeUpdate)) {
                throw new InvalidUpdateException(InvalidUpdateException.getInvalidUpdateDataMessage("attempt to update recipe id"));
            }
            else if (recipeUpdate.getUserId() != null || recipeUpdate.getUserName() != null) {
                throw new InvalidUpdateException(InvalidUpdateException.getInvalidUpdateDataMessage("attempt to update recipe author"));
            }
            else if (!isConsistentInstructionIdSet(existingRecipe.getRecipeInstructionIdSet(), recipeUpdate.getRecipeInstructionIdSet())) {
                throw new InvalidUpdateException(InvalidUpdateException.getInvalidUpdateDataMessage("instruction id set not found."));
            }
            else if (!isConsistentIngredientIdSet(existingRecipe.getRecipeIngredientIdSet(), recipeUpdate.getRecipeIngredientIdSet())) {
                throw new InvalidUpdateException(InvalidUpdateException.getInvalidUpdateDataMessage("ingredient id set not found."));
            }
            return true;
        }
        catch (DatabaseException|DataAccessException exception) {
            throw new InvalidUpdateException(InvalidUpdateException.getInvalidUpdateDataMessage("failed to validate the passed in recipe data."));
        }
    }

    private boolean containValidRecipeId(Integer recipeId, Recipe recipeUpdate) {
        return noRecipeIdUpdate(recipeUpdate) || sameRecipeId(recipeId, recipeUpdate);
    }

    private boolean noRecipeIdUpdate(Recipe recipeUpdate) {
        return recipeUpdate.getRecipeId() == null &&
            recipeUpdate.getRecipeInstructionList().stream().allMatch(instruction -> instruction.getRecipeId() == null) &&
            recipeUpdate.getRecipeIngredientList().stream().allMatch(ingredient -> ingredient.getRecipeId() == null);
    }

    private boolean sameRecipeId(Integer recipeId, Recipe recipeUpdate) {
        return recipeId.equals(recipeUpdate.getRecipeId()) &&
            recipeUpdate.getRecipeInstructionList().stream().allMatch(instruction -> recipeId.equals(instruction.getRecipeId())) &&
            recipeUpdate.getRecipeIngredientList().stream().allMatch(ingredient -> recipeId.equals(ingredient.getRecipeId()));
    }

    private boolean isConsistentInstructionIdSet(Set<Integer> existingInstructionSet, Set<Integer> instructionUpdateSet) {
        return existingInstructionSet.containsAll(instructionUpdateSet);
    }

    private boolean isConsistentIngredientIdSet(Set<Integer> existingIngredientSet, Set<Integer> ingredientUpdateSet) {
        return existingIngredientSet.containsAll(ingredientUpdateSet);
    }

}
