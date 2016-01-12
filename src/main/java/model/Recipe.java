package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by wendywang on 2015-11-07.
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class Recipe extends model.mapping.tables.pojos.Recipe{

    private String userName;
    private List<RecipeInstruction> recipeInstructionList = new ArrayList<>();
    private List<RecipeIngredient> recipeIngredientList = new ArrayList<>();

    public Recipe(){};

    public Recipe(Integer recipeId, String dishName, String summary, String servingSize, String dishImageUrl, int userId) {
        setRecipeId(recipeId);
        setDishName(dishName);
        setSummary(summary);
        setServing(servingSize);
        setDishImageUrl(dishImageUrl);
        setUserId(userId);
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
        setRecipeIdToRecipeIngredientList();
    }

    /**
     * set recipe id to the associated recipe instruction list
     */
    private void setRecipeIdToRecipeInstructionList() {
        for (RecipeInstruction instruction: recipeInstructionList) {
            instruction.setRecipeId(getRecipeId());
        }
    }

    /**
     * set recipe id to the associated recipe ingredient list
     */
    private void setRecipeIdToRecipeIngredientList() {
        for (RecipeIngredient ingredient: recipeIngredientList) {
            ingredient.setRecipeId(getRecipeId());
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
    @JsonProperty("user_id")
    public Integer getUserId(){
        return super.getUserId();
    }

    @Override
    @JsonProperty("user_id")
    public void setUserId(Integer userID){
        super.setUserId(userID);
    }

    @JsonProperty("user_name")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("user_name")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("recipe_instructions")
    public List<RecipeInstruction> getRecipeInstructionList() {
        return this.recipeInstructionList;
    }

    @JsonProperty("recipe_instructions")
    public void setRecipeInstructionList(List<RecipeInstruction> recipeInstructionList) {
        this.recipeInstructionList = recipeInstructionList;
    }

    @JsonProperty("recipe_ingredients")
    public List<RecipeIngredient> getRecipeIngredientList() {
        return this.recipeIngredientList;
    }

    @JsonProperty("recipe_ingredients")
    public void setRecipeIngredientList(List<RecipeIngredient> recipeIngredientList) {
        this.recipeIngredientList = recipeIngredientList;
    }

    public void addRecipeInstruction(RecipeInstruction recipeInstruction) {
        this.recipeInstructionList.add(recipeInstruction);
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        this.recipeIngredientList.add(recipeIngredient);
    }

    @JsonIgnore
    public Set<Integer> getRecipeInstructionIdSet() {
        return getRecipeInstructionList().stream()
            .map(RecipeInstruction::getInstructionId)
            .collect(Collectors.toSet());
    }

    @JsonIgnore
    public Set<Integer> getRecipeIngredientIdSet() {
        return getRecipeIngredientList().stream()
            .map(RecipeIngredient::getIngredientId)
            .collect(Collectors.toSet());
    }
}