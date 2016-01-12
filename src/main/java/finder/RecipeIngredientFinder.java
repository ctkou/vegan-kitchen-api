package finder;

import exception.DatabaseException;
import model.RecipeIngredient;

import java.util.List;

import static model.mapping.tables.RecipeIngredientTable.RECIPE_INGREDIENT;

/**
 * Created by adam on 24/12/15.
 */
public class RecipeIngredientFinder extends AbstractFinder {

    public List<RecipeIngredient> getIngredientListByRecipeId(int recipeId) throws DatabaseException {
        return getDataObjectList(RECIPE_INGREDIENT, RECIPE_INGREDIENT.RECIPE_ID.equal(recipeId), RecipeIngredient.class);
    }

    public void storeIngredientList(List<RecipeIngredient> ingredientList) throws DatabaseException {
        storeDataObjectList(RECIPE_INGREDIENT, ingredientList);
    }

    public void updateIngredientList(List<RecipeIngredient> ingredientList) throws DatabaseException {
        updateDataObjectList(RECIPE_INGREDIENT, ingredientList);
    }

}
