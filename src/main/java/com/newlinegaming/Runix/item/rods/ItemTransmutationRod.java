package com.newlinegaming.Runix.item.rods;

public class ItemTransmutationRod extends BaseRod {
    
    public ItemTransmutationRod() {
        setUnlocalizedName("runix:transmutationrod");
        setTextureName("minecraft:stick");//FIXME: LordIllyohs-Temporary till I fix the ItemRenderer for this
    }
    
    @Override
    public int getMaxEnrgy() {
        return 6000;
    }
}