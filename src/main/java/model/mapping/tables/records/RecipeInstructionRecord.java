/**
 * This class is generated by jOOQ
 */
package model.mapping.tables.records;


import javax.annotation.Generated;

import model.mapping.tables.RecipeInstructionTable;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


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
public class RecipeInstructionRecord extends UpdatableRecordImpl<RecipeInstructionRecord> implements Record4<Integer, Integer, String, String> {

	private static final long serialVersionUID = -1154867854;

	/**
	 * Setter for <code>vegan_kitchen_api.recipe_instruction.recipe_id</code>.
	 */
	public void setRecipeId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.recipe_instruction.recipe_id</code>.
	 */
	public Integer getRecipeId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>vegan_kitchen_api.recipe_instruction.instruction_id</code>.
	 */
	public void setInstructionId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.recipe_instruction.instruction_id</code>.
	 */
	public Integer getInstructionId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>vegan_kitchen_api.recipe_instruction.instruction</code>.
	 */
	public void setInstruction(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.recipe_instruction.instruction</code>.
	 */
	public String getInstruction() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>vegan_kitchen_api.recipe_instruction.image_url</code>.
	 */
	public void setImageUrl(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>vegan_kitchen_api.recipe_instruction.image_url</code>.
	 */
	public String getImageUrl() {
		return (String) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, Integer, String, String> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, Integer, String, String> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return RecipeInstructionTable.RECIPE_INSTRUCTION.RECIPE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return RecipeInstructionTable.RECIPE_INSTRUCTION.INSTRUCTION_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return RecipeInstructionTable.RECIPE_INSTRUCTION.INSTRUCTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return RecipeInstructionTable.RECIPE_INSTRUCTION.IMAGE_URL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getRecipeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getInstructionId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getInstruction();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getImageUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecipeInstructionRecord value1(Integer value) {
		setRecipeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecipeInstructionRecord value2(Integer value) {
		setInstructionId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecipeInstructionRecord value3(String value) {
		setInstruction(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecipeInstructionRecord value4(String value) {
		setImageUrl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecipeInstructionRecord values(Integer value1, Integer value2, String value3, String value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached RecipeInstructionRecord
	 */
	public RecipeInstructionRecord() {
		super(RecipeInstructionTable.RECIPE_INSTRUCTION);
	}

	/**
	 * Create a detached, initialised RecipeInstructionRecord
	 */
	public RecipeInstructionRecord(Integer recipeId, Integer instructionId, String instruction, String imageUrl) {
		super(RecipeInstructionTable.RECIPE_INSTRUCTION);

		setValue(0, recipeId);
		setValue(1, instructionId);
		setValue(2, instruction);
		setValue(3, imageUrl);
	}
}