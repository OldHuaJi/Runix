package com.newlinegaming.Runix.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IAetherSee {
    
    /*
     * Show the link between Runes
     */
    public boolean canShowLink(ItemStack itemStack, EntityPlayer player);
    
    /*
     * Can list runes Bound to the player
     */
//    public boolean canListBoundRunes(ItemStack itemStack, EntityPlayer player);
    
    /*
     * Shows the amount of energy held within a rune
     */
    public boolean canShowEnergy(ItemStack itemStack, EntityPlayer player);
    
    
}
