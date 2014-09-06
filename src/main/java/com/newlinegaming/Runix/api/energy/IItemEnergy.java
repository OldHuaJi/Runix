package com.newlinegaming.Runix.api.energy;

public interface IItemEnergy {
    
    /*
     * Gets the current amount of energy an item has
     */
    public int getCurrentEnergy(int current);
    
    /*
     * Sets the maximum energy an item can hold
     */
    public int getMaxEnrgy(int max);
    
    /*
     * Sets the minimum amount of energy and item can hold
     * Note: should always be zero
     */
    public int getMinEnergy(int min);
}
