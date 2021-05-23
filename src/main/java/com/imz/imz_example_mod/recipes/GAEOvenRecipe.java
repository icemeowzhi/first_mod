package com.imz.imz_example_mod.recipes;


import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;



public class GAEOvenRecipe implements IRecipeHandlerIMZ , IFuelTimeHandlerIMZ {

    //管理苹果炉的燃料和recipe
    //done in 5.15

     static class FuelTime implements IFuelTimeIMZ {
        Item input;
        int fuelTime;

         public FuelTime(Item input, int fuelTime) {
             this.input = input;
             this.fuelTime = fuelTime;
         }

         public Item getItem() {
             return input;
         }

         public int getFuelTime() {
             return fuelTime;
         }
     }

     static class Recipe implements IRecipeIMZ {
        final Item input;
        final Item output;
        final int burnTime;
        final String ID;

         public Recipe(Item input, Item output, int burnTime, String ID) {
             this.input = input;
             this.output = output;
             this.burnTime = burnTime;
             this.ID = ID;
         }

         public Item getInput() {
             return input;
         }

         public Item getOutput() {
             return output;
         }

         public int getProgress() {
            return burnTime;
        }

        public String getID() {
            return ID;
        }
    }

    private NonNullList<Recipe> recipes = NonNullList.create();
    private NonNullList<FuelTime> fuelTimes = NonNullList.create();


    public void regRecipes(Item input, Item output, int burnTime, String ID){
        recipes.add(new Recipe(input,output,burnTime,ID));
    }

    public void regFuels(Item input, int fuelTime){
        fuelTimes.add(new FuelTime(input,fuelTime));
    }

    public Item getRecipeOutputByInput(Item input){
         for (Recipe recipe : recipes){
             if (recipe.getInput().equals(input)){
                 return recipe.getOutput();
             }
         }
         return Items.AIR;
    }

    public int getProgressByInput(Item input){
        for (Recipe recipe : recipes){
            if (recipe.getInput().equals(input)){
                return recipe.getProgress();
            }
        }
        return 1;
    }

     public int getFuelTimeByInput(Item item){
         for (FuelTime fuelTime : fuelTimes){
             if (fuelTime.getItem().equals(item)){
                 return fuelTime.getFuelTime();
             }
         }
         return 1;
     }

     public boolean hasFuel(Item item){
        for (FuelTime fuelTime : fuelTimes){
            if (fuelTime.getItem().equals(item)){
                return true;
            }
        }
        return false;
     }

     public boolean hasRecipe(Item item){
        for (Recipe recipe : recipes){
            if (recipe.getInput().equals(item)){
                return true;
            }
        }
        return false;
     }
}
