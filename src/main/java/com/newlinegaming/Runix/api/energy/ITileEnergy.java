package com.newlinegaming.Runix.api.energy;

public interface ITileEnergy {

    /*
     * Gets the current amount of energy an item has
     */
    public int getCurrentEnergy();

    /*
     * Sets the maximum energy an item can hold
     */
    public int getMaxEnrgy();

    /*
     * Sets the minimum amount of energy and item can hold
     * Note: should always be zero
     */
    public int getMinEnergy();
    
    /*
     * Determines whether the tileentity can be broken without dropping its
     * contents
     */
    public boolean isHarvestable();

}
