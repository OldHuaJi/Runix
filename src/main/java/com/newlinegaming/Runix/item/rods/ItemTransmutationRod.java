package com.newlinegaming.Runix.item.rods;

import net.minecraft.item.ItemStack;

public class ItemTransmutationRod extends BaseRod {
    
    public ItemTransmutationRod() {
        setUnlocalizedName("runix:transmutationrod");
        setTextureName("minecraft:stick");//FIXME: LordIllyohs-Temporary till I fix the ItemRenderer for this
    }
    
    @Override
    public int getMaxEnergy(ItemStack is, int max) {
        return 6000;
    }
}