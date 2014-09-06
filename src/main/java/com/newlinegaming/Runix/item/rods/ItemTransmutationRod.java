package com.newlinegaming.Runix.item.rods;

public class ItemTransmutationRod extends BaseRod {
    
    public ItemTransmutationRod() {
        setUnlocalizedName("runix:transmutationrod");
        setTextureName("minecraft:stick");//FIXME: LordIllyohs-Temporary till I fix the ItemRenderer for this
        getMaxEnrgy(6000);
    }
    
//    @Override
//    public int getMaxEnrgy(int max) {
//        return max;
//    }
}