package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wendywang on 2015-11-07.
 */
@XmlRootElement
public class Recipe extends model.mapping.tables.pojos.Recipe{

    List<RecipeInstruction> recipeInstructionList = new ArrayList<>();
    List<Ingredient> ingredientList = new ArrayList<>();

    public Recipe(){};

    public Recipe(Integer recipeId, String dishName, String summary, String servingSize, String dishImageUrl, int authorUserId, int ingredientId) {
        setRecipeId(recipeId);
        setDishName(dishName);
        setSummary(summary);
        setServing(servingSize);
        setDishImageUrl(dishImageUrl);
        setAuthorUserId(authorUserId);
        setIngredientId(ingredientId);
    }

    @Override
    @JsonProperty("recipe_id")
    public Integer getRecipeId() {
        return super.getRecipeId();
    }

    @Override
    @JsonProperty("recipe_id")
    public void setRecipeId(Integer recipeId) {
        super.setRecipeId(recipeId);
        setRecipeIdToRecipeInstructionList();
    }

    /**
     * set recipe id to the associated recipe instruction list
     */
    private void setRecipeIdToRecipeInstructionList() {
        for (RecipeInstruction instruction: recipeInstructionList) {
            instruction.setRecipeId(getRecipeId());
        }
    }

    @Override
    @JsonProperty("dish_name")
    public String getDishName() {
        return super.getDishName();
    }

    @Override
    @JsonProperty("dish_name")
    public void setDishName(String dishName) {
        super.setDishName(dishName);
    }

    @Override
    @JsonProperty("summary")
    public String getSummary() {
        return super.getSummary();
    }

    @Override
    @JsonProperty("summary")
    public void setSummary(String summary) {
        super.setSummary(summary);
    }

    @Override
    @JsonProperty("serving")
    public String getServing() {
        return super.getServing();
    }

    @Override
    @JsonProperty("serving")
    public void setServing(String serving) {
        super.setServing(serving);
    }

    @Override
    @JsonProperty("dish_image_url")
    public String getDishImageUrl() {
        return super.getDishImageUrl();
    }

    @Override
    @JsonProperty("dish_image_url")
    public void setDishImageUrl(String dishImageURL) {
        super.setDishImageUrl(dishImageURL);
    }

    @Override
    @JsonProperty("author_id")
    public Integer getAuthorUserId(){
        return super.getAuthorUserId();
    }

    @Override
    @JsonProperty("author_id")
    public void setAuthorUserId(Integer authorUserID){
        super.setAuthorUserId(authorUserID);
    }

    @JsonProperty("recipe_instructions")
    public List<RecipeInstruction> getRecipeInstructionList() {
        return this.recipeInstructionList;
    }

    @JsonProperty("recipe_instructions")
    public void setRecipeInstructionList(List<RecipeInstruction> recipeInstructionList) {
        this.recipeInstructionList = recipeInstructionList;
    }

    public void addRecipeInstructions(RecipeInstruction recipeInstruction) {
        this.recipeInstructionList.add(recipeInstruction);
    }

    @JsonProperty("ingredient_id")
    public List<Ingredient> getIngredientList()
    {
        return this.ingredientList;
    }

    @JsonProperty("ingredient_id")
    public void setIngredientList(List<Ingredient> ingredientList)
    {
        this.ingredientList = ingredientList;
    }

    public void addIngredients(Ingredient ingredient)
    {
        this.ingredientList.add(ingredient);
    }
}