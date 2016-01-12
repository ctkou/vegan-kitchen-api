package manager;

import exception.DatabaseException;
import finder.RecipeIngredientFinder;
import model.RecipeIngredient;

import java.util.List;

/**
 * Created by wendywang on 2015-11-14.
 */
public class RecipeIngredientManager {

    RecipeIngredientFinder ingredientFinder = new RecipeIngredientFinder();

    public List<RecipeIngredient> getIngredientListByRecipeId(Integer recipeId) throws DatabaseException {
        return ingredientFinder.getIngredientListByRecipeId(recipeId);
    }

    public void addRecipeIngredientList(Integer recipeId, List<RecipeIngredient> ingredientList) throws DatabaseException {
        ingredientList.stream().forEach(ingredient -> ingredient.setRecipeId(recipeId));
        ingredientFinder.storeIngredientList(ingredientList);
    }

    public void putRecipeIngredientList(List<RecipeIngredient> ingredientListUpdate) throws DatabaseException {
        ingredientFinder.updateIngredientList(ingredientListUpdate);
    }

}
