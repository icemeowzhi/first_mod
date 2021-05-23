package com.imz.imz_example_mod.recipes;

import net.minecraft.item.Item;

public interface IFuelTimeHandlerIMZ {
    void regFuels(Item input, int fuelTime);
    boolean hasFuel(Item item);
    int getFuelTimeByInput(Item item);
}
