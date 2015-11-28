/**
 * This class is generated by jOOQ
 */
package model.mapping.tables;


import model.mapping.Keys;
import model.mapping.VeganKitchenApiSchema;
import model.mapping.tables.records.RecipeRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


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
public class RecipeTable extends TableImpl<RecipeRecord> {

	private static final long serialVersionUID = 1293236300;

	/**
	 * The reference instance of <code>vegan_kitchen_api.recipe</code>
	 */
	public static final RecipeTable RECIPE = new RecipeTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<RecipeRecord> getRecordType() {
		return RecipeRecord.class;
	}

	/**
	 * The column <code>vegan_kitchen_api.recipe.recipe_id</code>.
	 */
	public final TableField<RecipeRecord, Integer> RECIPE_ID = createField("recipe_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.recipe.dish_name</code>.
	 */
	public final TableField<RecipeRecord, String> DISH_NAME = createField("dish_name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.recipe.summary</code>.
	 */
	public final TableField<RecipeRecord, String> SUMMARY = createField("summary", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.recipe.serving</code>.
	 */
	public final TableField<RecipeRecord, String> SERVING = createField("serving", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.recipe.dish_image_url</code>.
	 */
	public final TableField<RecipeRecord, String> DISH_IMAGE_URL = createField("dish_image_url", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>vegan_kitchen_api.recipe.author_user_id</code>.
	 */
	public final TableField<RecipeRecord, Integer> AUTHOR_USER_ID = createField("author_user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>vegan_kitchen_api.recipe</code> table reference
	 */
	public RecipeTable() {
		this("recipe", null);
	}

	/**
	 * Create an aliased <code>vegan_kitchen_api.recipe</code> table reference
	 */
	public RecipeTable(String alias) {
		this(alias, RECIPE);
	}

	private RecipeTable(String alias, Table<RecipeRecord> aliased) {
		this(alias, aliased, null);
	}

	private RecipeTable(String alias, Table<RecipeRecord> aliased, Field<?>[] parameters) {
		super(alias, VeganKitchenApiSchema.VEGAN_KITCHEN_API, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<RecipeRecord, Integer> getIdentity() {
		return Keys.IDENTITY_RECIPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<RecipeRecord> getPrimaryKey() {
		return Keys.KEY_RECIPE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<RecipeRecord>> getKeys() {
		return Arrays.<UniqueKey<RecipeRecord>>asList(Keys.KEY_RECIPE_PRIMARY, Keys.KEY_RECIPE_RECIPE_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecipeTable as(String alias) {
		return new RecipeTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public RecipeTable rename(String name) {
		return new RecipeTable(name, null);
	}
}
