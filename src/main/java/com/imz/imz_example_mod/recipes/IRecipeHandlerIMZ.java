package com.imz.imz_example_mod.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public interface IRecipeHandlerIMZ {
    void regRecipes(Item input, Item output, int burnTime, String ID);
    boolean hasRecipe(Item item);
    Item getRecipeOutputByInput(Item input);
    int getProgressByInput(Item input);
}
