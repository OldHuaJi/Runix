package com.newlinegaming.Runix.api.energy;

import net.minecraft.item.ItemStack;

public interface IItemEnergy {
    
    /*
     * Gets the current amount of energy an item has
     */
    public int getCurrentEnergy(ItemStack is);

    public int setCurrentEnergy(ItemStack is, int current);

    /*
     * Sets the maximum energy an item can hold
     */
    public int getMaxEnergy(ItemStack is, int max);

}
