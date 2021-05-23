package com.imz.imz_example_mod.recipes;

import net.minecraft.item.Item;

public interface IRecipeIMZ {
    public Item getInput();
    public Item getOutput();
    public String getID();
    public int getProgress();

}
