package manager;

import factory.database.ConnectionFactory;
import model.RecipeIngredient;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.List;

import static model.mapping.tables.RecipeIngredientTable.RECIPE_INGREDIENT;

/**
 * Created by wendywang on 2015-11-14.
 */
public class RecipeIngredientManager {

    public List<RecipeIngredient> getRecipeIngredientByRecipeId(Integer recipeId) throws Exception
    {
        try (Connection connection = ConnectionFactory.getConnection())
        {
            DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
            return create.select().from(RECIPE_INGREDIENT).where(RECIPE_INGREDIENT.RECIPE_ID.equal(recipeId)).fetchInto(RecipeIngredient.class);
        }
    }



}