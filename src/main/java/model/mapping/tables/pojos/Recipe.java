/**
 * This class is generated by jOOQ
 */
package model.mapping.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


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
public class Recipe implements Serializable {

	private static final long serialVersionUID = 1895554309;

	private Integer recipeId;
	private String  dishName;
	private String  summary;
	private String  serving;
	private String  dishImageUrl;
	private Integer authorUserId;

	public Recipe() {}

	public Recipe(Recipe value) {
		this.recipeId = value.recipeId;
		this.dishName = value.dishName;
		this.summary = value.summary;
		this.serving = value.serving;
		this.dishImageUrl = value.dishImageUrl;
		this.authorUserId = value.authorUserId;
	}

	public Recipe(
		Integer recipeId,
		String  dishName,
		String  summary,
		String  serving,
		String  dishImageUrl,
		Integer authorUserId
	) {
		this.recipeId = recipeId;
		this.dishName = dishName;
		this.summary = summary;
		this.serving = serving;
		this.dishImageUrl = dishImageUrl;
		this.authorUserId = authorUserId;
	}

	public Integer getRecipeId() {
		return this.recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getDishName() {
		return this.dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getServing() {
		return this.serving;
	}

	public void setServing(String serving) {
		this.serving = serving;
	}

	public String getDishImageUrl() {
		return this.dishImageUrl;
	}

	public void setDishImageUrl(String dishImageUrl) {
		this.dishImageUrl = dishImageUrl;
	}

	public Integer getAuthorUserId() {
		return this.authorUserId;
	}

	public void setAuthorUserId(Integer authorUserId) {
		this.authorUserId = authorUserId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Recipe (");

		sb.append(recipeId);
		sb.append(", ").append(dishName);
		sb.append(", ").append(summary);
		sb.append(", ").append(serving);
		sb.append(", ").append(dishImageUrl);
		sb.append(", ").append(authorUserId);

		sb.append(")");
		return sb.toString();
	}
}
