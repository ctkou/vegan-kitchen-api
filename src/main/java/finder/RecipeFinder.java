package finder;

import exception.DatabaseException;
import finder.utility.JoinClause;
import model.Recipe;
import org.jooq.Field;

import java.util.*;

import static model.mapping.tables.RecipeTable.RECIPE;
import static model.mapping.tables.UserTable.USER;

/**
 * Created by adam on 24/12/15.
 */
public class RecipeFinder extends AbstractFinder {

    private JoinClause recipeToUserJoin = new JoinClause(USER, RECIPE.USER_ID.equal(USER.USER_ID));
    private static final Map<String, Field<?>> orderByMap;

    static {
        Map<String, Field<?>> orderByFields = new HashMap<>();
        orderByFields.put("recipe_id", RECIPE.RECIPE_ID);
        orderByFields.put("dish_name", RECIPE.DISH_NAME);
        orderByFields.put("summary", RECIPE.SUMMARY);
        orderByFields.put("serving", RECIPE.SERVING);
        orderByFields.put("user_name", USER.USER_NAME);
        orderByMap = Collections.unmodifiableMap(orderByFields);
    }

    public List<Recipe> getRecipes(int limit, int offset, String orderByField) throws DatabaseException {
        List<JoinClause> joins = new ArrayList<>();
        joins.add(recipeToUserJoin);
        return getDataObjectList(RECIPE, joins, limit, offset, orderByMap.get(orderByField).asc(), Recipe.class);
    }

    public Recipe findRecipeById(int recipeId) throws DatabaseException {
        List<JoinClause> joins = new ArrayList<>();
        joins.add(recipeToUserJoin);
        return getDataObject(RECIPE, joins, RECIPE.RECIPE_ID.equal(recipeId), Recipe.class);
    }

    public Integer storeRecipe(Recipe recipe) throws DatabaseException {
        return storeDataObject(RECIPE, recipe, RECIPE.RECIPE_ID);
    }

    public void updateRecipe(Recipe recipeUpdate) throws DatabaseException {
        updateDataObject(RECIPE, recipeUpdate);
    }

}
