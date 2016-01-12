package manager;

import finder.AbstractFinder;
import exception.DatabaseException;
import model.RecipeInstruction;

import java.util.List;

import static model.mapping.tables.RecipeInstructionTable.RECIPE_INSTRUCTION;

/**
 * Created by wendywang on 2015-11-14.
 */
public class RecipeInstructionManager {

    public List<RecipeInstruction> getInstructionListByRecipeId(Integer recipeId) throws DatabaseException {
        return AbstractFinder.getDataObjectList(RECIPE_INSTRUCTION, RECIPE_INSTRUCTION.RECIPE_ID.equal(recipeId), RecipeInstruction.class);
    }

    public void addRecipeInstructionList(Integer recipeId, List<RecipeInstruction> instructionList) throws DatabaseException {
        instructionList.stream().forEach(instruction -> instruction.setRecipeId(recipeId));
        AbstractFinder.storeDataObjectList(RECIPE_INSTRUCTION, instructionList);
    }

    public void putRecipeInstructionList(List<RecipeInstruction> instructionListUpdate) throws DatabaseException {
        AbstractFinder.updateDataObjectList(RECIPE_INSTRUCTION, instructionListUpdate);
    }

}
