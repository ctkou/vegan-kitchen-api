/**
 * This class is generated by jOOQ
 */
package model.mapping.tables;


import javax.annotation.Generated;

import model.mapping.VeganKitchenApiSchema;
import model.mapping.tables.records.IngredientRecipeRecord;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IngredientRecipeTable extends TableImpl<IngredientRecipeRecord> {

	private static final long serialVersionUID = -1960876888;

	/**
	 * The reference instance of <code>vegan_kitchen_api.ingredient_recipe</code>
	 */
	public static final IngredientRecipeTable INGREDIENT_RECIPE = new IngredientRecipeTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<IngredientRecipeRecord> getRecordType() {
		return IngredientRecipeRecord.class;
	}

	/**
	 * The column <code>vegan_kitchen_api.ingredient_recipe.name</code>.
	 */
	public final TableField<IngredientRecipeRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.ingredient_recipe.recipe_id</code>.
	 */
	public final TableField<IngredientRecipeRecord, Integer> RECIPE_ID = createField("recipe_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.ingredient_recipe.image_url</code>.
	 */
	public final TableField<IngredientRecipeRecord, String> IMAGE_URL = createField("image_url", org.jooq.impl.SQLDataType.VARCHAR.length(225), this, "");

	/**
	 * Create a <code>vegan_kitchen_api.ingredient_recipe</code> table reference
	 */
	public IngredientRecipeTable() {
		this("ingredient_recipe", null);
	}

	/**
	 * Create an aliased <code>vegan_kitchen_api.ingredient_recipe</code> table reference
	 */
	public IngredientRecipeTable(String alias) {
		this(alias, INGREDIENT_RECIPE);
	}

	private IngredientRecipeTable(String alias, Table<IngredientRecipeRecord> aliased) {
		this(alias, aliased, null);
	}

	private IngredientRecipeTable(String alias, Table<IngredientRecipeRecord> aliased, Field<?>[] parameters) {
		super(alias, VeganKitchenApiSchema.VEGAN_KITCHEN_API, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IngredientRecipeTable as(String alias) {
		return new IngredientRecipeTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public IngredientRecipeTable rename(String name) {
		return new IngredientRecipeTable(name, null);
	}
}